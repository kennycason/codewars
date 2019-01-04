package _5kyu;

public class PeanoNumbers {
    interface Peano {
        class Zero implements Peano {
            public static final Zero INSTANCE = new Zero();
            private Zero() {}
        }

        class Succ implements Peano {
            public final Peano peano;
            public Succ(Peano peano) {
                this.peano = peano;
            }
        }
    }

    enum Ordering { GT, LT, EQ }

    public static Peano inc(Peano peano) {
        return new Peano.Succ(peano);
    }

    public static Peano dec(Peano peano) {
        if (compare(peano, Peano.Zero.INSTANCE) == Ordering.EQ) {
            throw new ArithmeticException("negative number");
        }
        return ((Peano.Succ) peano).peano;
    }

    public static boolean isZero(Peano peano) {
        return compare(peano, Peano.Zero.INSTANCE) == Ordering.EQ;
    }


    public static Peano add(Peano a, Peano b) {
        while (!isZero(b)) {
            a = inc(a);
            b = dec(b);
        }
        return a;
    }

    public static Peano sub(Peano a, Peano b) {
        while (true) {
            if (isZero(b)) {
                return a;
            }
            if (isZero(a) && !isZero(b)) {
                throw new ArithmeticException("negative number");
            }
            a = dec(a);
            b = dec(b);
        }
    }

    public static Peano mul(Peano a, Peano b) {
        Peano total = Peano.Zero.INSTANCE;
        while (!isZero(b)) {
            total = add(total, a);
            b = dec(b);
        }
        return total;
    }

    public static Peano div(Peano a, Peano b) {
        if (isZero(b)) {
            throw new ArithmeticException("divide by 0");
        }
        Peano n = Peano.Zero.INSTANCE;
        while (compare(a, b) != Ordering.LT) {
            n = inc(n);
            a = sub(a, b);
        }
        return n;
    }

    public static boolean even(Peano peano) {
        while (!isZero(peano)) {
            peano = dec(peano);
            if (isZero(peano)) {
                return false;
            }
            peano = dec(peano);
        }
        return true;
    }

    public static boolean odd(Peano peano) {
        return !even(peano);
    }

    public static Ordering compare(Peano a, Peano b) {
        while (true) {
            if (a.equals(Peano.Zero.INSTANCE) && b.equals(Peano.Zero.INSTANCE)) {
                return Ordering.EQ;
            }
            if (a.equals(Peano.Zero.INSTANCE)) {
                return Ordering.LT;
            }
            if (b.equals(Peano.Zero.INSTANCE)) {
                return Ordering.GT;
            }
            a = dec(a);
            b = dec(b);
        }
    }
}
