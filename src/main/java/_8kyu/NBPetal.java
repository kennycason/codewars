package _8kyu;

/**
 * Created by kenny on 7/16/17.
 */
public class NBPetal {
    public static String howMuchILoveYou(final int nbPetals) {
        switch (nbPetals % 6) {
            case 0: return "not at all";
            case 1: return "I love you";
            case 2: return "a little";
            case 3: return "a lot";
            case 4: return "passionately";
            case 5: return "madly";
            default: throw new RuntimeException("I hate you");
        }
    }
}