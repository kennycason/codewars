package _2kyu;

import java.util.HashMap;
import java.util.Map;

public class BrainLuck {

    private final String instructions;

    public BrainLuck(final String instructions) {
        this.instructions = instructions;
    }

    public String process(final String input) {
        final StringBuilder stdOut = new StringBuilder();
        final Memory memory = new Memory();
        int bytesRead = 0;  // bytes read from input
        int dp = 0;         // data pointer (memory pointer)
        int ip = 0;         // instruction pointer

        while (ip < instructions.length()) {
            final char token = instructions.charAt(ip);
            if (token == '>')      { dp++; }
            else if (token == '<') { dp--; }
            else if (token == '+') { memory.inc(dp); }
            else if (token == '-') { memory.dec(dp); }
            else if (token == '.') { stdOut.append((char) memory.read(dp)); }
            else if (token == ',') { memory.write(dp, (int) input.charAt(bytesRead++)); }
            else if (token == '[') {
                if (memory.read(dp) == 0) { //  then skip to the next matching ']'
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
                if (memory.read(dp) != 0) { // loopback to the previous matching'['
                    int bracketCount = 1;
                    while (bracketCount > 0) {
                        final char subToken = instructions.charAt(--ip);
                        if (subToken == ']') {
                            bracketCount++;
                        } else if (subToken == '[') {
                            bracketCount--;
                        }
                    }
                }
            } else {
                // ignore unknown characters
            }
            ip++;
        }
        return stdOut.toString();
    }

    private static class Memory {
        private final Map<Integer, Integer> memory = new HashMap<>();

        public void inc(final int address) {
            zeroAddress(address);
            if (memory.get(address) == 255) {
                memory.put(address, 0);
            } else {
                memory.put(address, memory.get(address) + 1);
            }
        }

        public void dec(final int address) {
            zeroAddress(address);
            if (memory.get(address) == 0) {
                memory.put(address, 255);
            } else {
                memory.put(address, memory.get(address) - 1);
            }
        }

        public int read(final int address) {
            zeroAddress(address);
            return memory.get(address);
        }

        public void write(final int address, final int value) {
            zeroAddress(address);
            memory.put(address, value);
        }

        private void zeroAddress(final int address) {
            if (!memory.containsKey(address)) {
                memory.put(address, 0);
            }
        }

    }

}