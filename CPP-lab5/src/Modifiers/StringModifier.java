package Modifiers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringModifier {
    public static String UppercaseLastCharInWords(String text) {
        final String regex = "([a-z])(?!\\B)";
        String string = text;

        final Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            if (matcher.groupCount() > 0) {
                string = matcher.replaceFirst(matcher.group(0).toUpperCase());
                matcher = pattern.matcher(string);
            }
        }

        return string;
    }
}
