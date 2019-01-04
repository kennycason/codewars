package _4kyu;

import _4kyu.Simplexer.Token;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Simplexer implements Iterator<Token> {

    public static class Token {
        public final String text;
        public final String type; // would prefer this to be an enum
        public Token(final String text, final String type) {
            this.text = text;
            this.type = type;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) { return true; }
            if (o == null || getClass() != o.getClass()) { return false; }

            final Token token = (Token) o;

            if (text != null ? !text.equals(token.text) : token.text != null) { return false; }
            return type != null ? type.equals(token.type) : token.type == null;
        }

    }

    public static class TokenParser {
        public final String name;
        public final String pattern;

        public TokenParser(final String name, final String pattern) {
            this.name = name;
            this.pattern = pattern;
        }
    }

    private static final TokenParser OPERATOR = new TokenParser("operator", "[\\+|\\-|\\*|\\/|%|\\(|\\)|=]");
    private static final TokenParser WHITESPACE = new TokenParser("whitespace", "[ \\t\\f\\r\\n]+");
    private static final TokenParser INTEGER = new TokenParser("integer", "[0-9]+");
    private static final TokenParser KEYWORD = new TokenParser("keyword", "if|else|for|while|return|func|break");
    private static final TokenParser BOOLEAN = new TokenParser("boolean", "true|false");
    private static final TokenParser IDENTIFIER = new TokenParser("identifier", "[a-zA-Z_$][a-zA-Z_$0-9]*");
    private static final TokenParser STRING = new TokenParser("string", "\".*?\"");

    private static final List<TokenParser> TOKEN_PARSERS = Arrays.asList(
            OPERATOR,
            WHITESPACE,
            INTEGER,
            KEYWORD,
            BOOLEAN,
            IDENTIFIER,
            STRING
    );

    private static final Pattern MERGED_TOKEN_PARSER = buildMergedTokenParser();

    // ensure matcher groups are named for easy reference
    private static Pattern buildMergedTokenParser() {
        final StringBuilder tokenPatterns = new StringBuilder();
        for (final TokenParser tokenParser : TOKEN_PARSERS) {
            tokenPatterns.append("|(?<");
            tokenPatterns.append(tokenParser.name);
            tokenPatterns.append(">");
            tokenPatterns.append(tokenParser.pattern);
            tokenPatterns.append(")");
        }
        return Pattern.compile(
                tokenPatterns.substring(1)); // remove prefixing "|"
    }

    private final List<Token> tokens;
    private int index;

    public Simplexer(final String input) {
        this.tokens = lex(input);
    }

    @Override
    public boolean hasNext() {
        return index < tokens.size();
    }

    @Override
    public Token next() {
        return tokens.get(index++);
    }

    private static List<Token> lex(final String input) {
        if (input == null || input.isEmpty()) {
            return Collections.emptyList();
        }
        final List<Token> tokens = new ArrayList<>();
        final Matcher lexerMatcher = MERGED_TOKEN_PARSER.matcher(input);
        while (lexerMatcher.find()) {
            for (final TokenParser tokenParser : TOKEN_PARSERS) {
                if (lexerMatcher.group(tokenParser.name) != null) {
                    tokens.add(new Token(lexerMatcher.group(tokenParser.name), tokenParser.name));
                }
            }
        }
        return tokens;
    }

}