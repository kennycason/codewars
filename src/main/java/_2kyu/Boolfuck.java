package _2kyu;

import java.util.*;

@SuppressWarnings("Duplicates")
public class Boolfuck {

    public static String interpret(final String instructions, final String input) {
        return interpret(instructions, toBits(input));
    }

    public static String interpret(final String instructions, final List<Boolean> input) {
        // use list over bitset due to adding false values to bitset not increasing length
        final List<Boolean> stdOut = new ArrayList<>();
        final Memory memory = new Memory();
        int bitsRead = 0;    // bit read from input
        int dp = 0;          // data pointer (memory pointer)
        int ip = 0;          // instruction pointer

        while (ip < instructions.length()) {
            final char token = instructions.charAt(ip);
            if (token == '>')      { dp++; }
            else if (token == '<') { dp--; }
            else if (token == '+') { memory.flip(dp); }
            else if (token == ';') { stdOut.add(memory.read(dp));}
            else if (token == ',') { memory.write(dp, (bitsRead < input.size()) ? input.get(bitsRead++) : false); }
            else if (token == '[') {
                if (!memory.read(dp)) { //  then skip to the next matching ']'
                    int bracketCount = 1;
                    while (bracketCount > 0) {
                        final char subToken = instructions.charAt(++ip);
                        if (subToken == '[') {
                            bracketCount++;
                        } else if (subToken == ']') {
                            bracketCount--;
                        }
                    }
                }
            }
            else if (token == ']') {
                // loopback to the previous matching'['
                int bracketCount = 1;
                while (bracketCount > 0) {
                    final char subToken = instructions.charAt(--ip);
                    if (subToken == ']') {
                        bracketCount++;
                    } else if (subToken == '[') {
                        bracketCount--;
                    }
                }
                ip--; // ensure we start at '['

            }
            ip++;
        }
        return formatOutput(stdOut);
    }

    private static String formatOutput(final List<Boolean> stdOut) {
        // pad with zeros
        while (stdOut.size() % 8 != 0) {
            stdOut.add(false);
        }
        // prepare output
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stdOut.size(); i += 8) {
            int c = 0;
            for (int j = 0; j < 8; j++) {
                c += (stdOut.get(i + j) ? 1 : 0) << j;
            }
            stringBuilder.append((char) c);
        }
        return stringBuilder.toString();
    }

    public static List<Boolean> toBits(final String input) {
        final List<Boolean> bits = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            int c = (int) input.charAt(i);
            for (int j = 0; j < 8; j++) {
                bits.add((c & 1) == 1);
                c >>= 1;
            }
        }
        return bits;
    }

    private static class Memory {
        // need to support negative and "infinite" growth
        private final Map<Integer, Boolean> memory = new HashMap<>();

        public void flip(final int address) {
            initMemory(address);
            write(address, !read(address));
        }

        public boolean read(final int address) {
            initMemory(address);
            return memory.get(address);
        }

        public void write(final int address, final boolean value) {
            initMemory(address);
            memory.put(address, value);
        }

        private void initMemory(final int address) {
            if (!memory.containsKey(address)) {
                memory.put(address, false);
            }
        }

    }

}