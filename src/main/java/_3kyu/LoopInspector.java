package _3kyu;

import java.util.HashMap;
import java.util.Map;

public class LoopInspector {

    // stubbing out so i can write code
    public static class Node {
        public Node getNext() {
            return null;
        }
    }

    public int loopSize(final Node node) {
        // iterate to find loop start/end
        final Map<Node, Integer> visited = new HashMap<>();
        Node prev = node;
        Node next = null;
        while ((next = prev.getNext()) != null) {
            if (visited.containsKey(prev)) {
                return (visited.size() + 1) - visited.get(prev);
            }
            visited.put(prev, visited.size() + 1);
            prev = next;
        }
        return -1;
    }
//
//    public int loopSize(final Node node) {
//        // iterate to find loop start/end
//        final Set<Node> visited = new HashSet<>();
//        Node prev = node;
//        Node next = null;
//        Node start = null;
//        Node end = null;
//        while ((next = prev.getNext()) != null) {
//            if (visited.contains(prev)) {
//                System.out.println("break! hit loop!");
//                start = next;
//                end = prev;
//                break;
//            }
//            visited.add(prev);
//            prev = next;
//        }
//        // count length
//        int i = 0;
//        while (!start.equals(end)) {
//            i++;
//            start = start.getNext();
//        }
//
//        return i;
//    }

}