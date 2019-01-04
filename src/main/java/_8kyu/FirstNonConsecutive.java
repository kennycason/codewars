package _8kyu;

class FirstNonConsecutive {
    static Integer find(final int[] array) {
        for (int i = 0, j = 1; j < array.length; j++) {
            if (array[i] + 1 != array[j]) {
                return array[j];
            }
        }
        return null;
    }
}
