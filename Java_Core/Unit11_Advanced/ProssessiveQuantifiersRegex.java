package Unit11_Advanced;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ProssesstiveQuantifiersRegex {
    public static void main(String[] args) {
        String text= "xxxjavaxxxxxxjava";
        Pattern pattern = Pattern.compile(".*+va",Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(text);
        System.out.println("Possessive --->"+matcher.find());

    }
}