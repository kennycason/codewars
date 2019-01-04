package _2kyu;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathEvaluator {

    public double calculate(String expression) {
        System.out.println(new Lexer(expression));
        return 0L;
    }

    // pulled & modified from my "simplexer problem" :)
    public static class Token {
        public final String text;
        public final TokenType type;
        public Token(final String text, final TokenType type) {
            this.text = text;
            this.type = type;
        }
    }

    // lexer stuff

    public enum TokenType {
        MULTIPLY,
        DIVISION,
        ADDITION,
        SUBTRACTION,
        NUMBER,
        WHITESPACE,
        LEFTPARENTHESIS,
        RIGHTPARENTHESIS
    }
    
    public static class Lexer implements Iterator<Token> {

        public static class TokenParser {
            public final TokenType name;
            public final String pattern;

            public TokenParser(final TokenType name, final String pattern) {
                this.name = name;
                this.pattern = pattern;
            }
        }

        private static final TokenParser MULTIPLY = new TokenParser(TokenType.MULTIPLY, "\\*");
        private static final TokenParser DIVISION = new TokenParser(TokenType.DIVISION, "\\/");
        private static final TokenParser ADDITION = new TokenParser(TokenType.ADDITION, "\\+");
        private static final TokenParser SUBTRACTION = new TokenParser(TokenType.SUBTRACTION, "\\-");
        private static final TokenParser LEFT_PARENTHESIS = new TokenParser(TokenType.LEFTPARENTHESIS, "\\(");
        private static final TokenParser RIGHT_PARENTHESIS = new TokenParser(TokenType.RIGHTPARENTHESIS, "\\)");
        private static final TokenParser WHITESPACE = new TokenParser(TokenType.WHITESPACE, "[ \\t\\f\\r\\n]+");
        private static final TokenParser NUMBER = new TokenParser(TokenType.NUMBER, "-?(\\d*\\.)?\\d+");

        private static final List<TokenParser> TOKEN_PARSERS = Arrays.asList(
                NUMBER,
                MULTIPLY,
                DIVISION,
                ADDITION,
                SUBTRACTION,
                WHITESPACE,
                LEFT_PARENTHESIS,
                RIGHT_PARENTHESIS
        );

        private static final Pattern MERGED_TOKEN_PARSER = buildMergedTokenParser();

        // ensure matcher groups are named for easy reference
        private static Pattern buildMergedTokenParser() {
            final StringBuilder tokenPatterns = new StringBuilder();
            for (final TokenParser tokenParser : TOKEN_PARSERS) {
                tokenPatterns.append("|(?<");
                tokenPatterns.append(tokenParser.name.name());
                tokenPatterns.append(">");
                tokenPatterns.append(tokenParser.pattern);
                tokenPatterns.append(")");
            }
            return Pattern.compile(
                    tokenPatterns.substring(1)); // remove prefixing "|"
        }

        private final List<Token> tokens;
        private int index;

        public Lexer(final String expression) {
            this.tokens = lex(expression);
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
                    if (lexerMatcher.group(tokenParser.name.name()) != null) {
                        tokens.add(new Token(lexerMatcher.group(tokenParser.name.name()), tokenParser.name));
                    }
                }
            }
            return tokens;
        }

    }

    // parser stuff
    public class AstNode {
        private final Token token;

        private final List<AstNode> children = new ArrayList<>();

        public AstNode(final Token token, final AstNode... children) {
            this.token = token;
            for (final AstNode child : children) {
                this.children.add(child);
            }
        }

        public Token getToken() {
            return token;
        }

        public List<AstNode> getChildren() {
            return children;
        }
    }

    public static class Parser {


    }
}