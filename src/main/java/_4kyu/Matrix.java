package _4kyu;

public class Matrix {

    public static void enter() {
        destroyTheUniverse(new Neo());
    }

    private static <T extends Throwable> void destroyTheUniverse(final Throwable neo) throws T {
        throw (T) neo;
    }

}
