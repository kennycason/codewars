package _8kyu;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kenny on 7/16/17.
 */
public class Welcome {
    private static final String DEFAULT_LANGUAGE = "english";
    private static final Map<String, String> LANGUAGE_DICTIONARY = new HashMap<>();
    static {
        LANGUAGE_DICTIONARY.put("english", "Welcome");
        LANGUAGE_DICTIONARY.put("czech", "Vitejte");
        LANGUAGE_DICTIONARY.put("danish", "Velkomst");
        LANGUAGE_DICTIONARY.put("dutch", "Welkom");
        LANGUAGE_DICTIONARY.put("estonian", "Tere tulemast");
        LANGUAGE_DICTIONARY.put("finnish", "Tervetuloa");
        LANGUAGE_DICTIONARY.put("flemish", "Welgekomen");
        LANGUAGE_DICTIONARY.put("french", "Bienvenue");
        LANGUAGE_DICTIONARY.put("german", "Willkommen");
        LANGUAGE_DICTIONARY.put("irish", "Failte");
        LANGUAGE_DICTIONARY.put("italian", "Benvenuto");
        LANGUAGE_DICTIONARY.put("latvian", "Gaidits");
        LANGUAGE_DICTIONARY.put("lithuanian", "Laukiamas");
        LANGUAGE_DICTIONARY.put("polish", "Witamy");
        LANGUAGE_DICTIONARY.put("spanish", "Bienvenido");
        LANGUAGE_DICTIONARY.put("swedish", "Valkommen");
        LANGUAGE_DICTIONARY.put("welsh", "Croeso");
    }

    public static String greet(final String language) {
        if (LANGUAGE_DICTIONARY.containsKey(language)) {
            return LANGUAGE_DICTIONARY.get(language);
        }
        return LANGUAGE_DICTIONARY.get(DEFAULT_LANGUAGE);
    }

}