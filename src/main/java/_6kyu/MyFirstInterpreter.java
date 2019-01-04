package _6kyu;

public class MyFirstInterpreter {

    private final String code;

    public MyFirstInterpreter(final String code) {
        this.code = code;
    }

    public String interpret() {
        final StringBuilder output = new StringBuilder();
        int memory = 0;

        for (int i = 0; i < code.length(); i++) {
            switch (code.charAt(i)) {
                case '+':
                    memory = (memory + 1) % 256;
                    break;

                case '.':
                    output.append((char) memory);
                    break;

                default:
                    // ignore
            }
        }
        return output.toString();
    }

}