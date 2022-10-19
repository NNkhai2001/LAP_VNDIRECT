package Unit11_Advanced;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class QuantifiersREgex {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("t+\\S+(\\S+)(.*)",Pattern.UNICODE_CHARACTER_CLASS);
        String text = "tớ học java";
        Matcher matcher = pattern.matcher(text);
        System.out.println(text+"---->"+matcher.matches());
        text = "cậu học java";
        matcher = pattern.matcher(text);
        System.out.println(text+"---->"+matcher.matches());
        text = "tôi học java";
        matcher = pattern.matcher(text);
        System.out.println(text+"---->"+matcher.matches());


    }

}