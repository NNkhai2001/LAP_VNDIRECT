package Unit11_Advanced;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class GroupRegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(.*)(.*)",Pattern.UNICODE_CHARACTER_CLASS);
        String text = "tôi học java";
        Matcher matcher = pattern.matcher(text);
        System.out.println(matcher.matches());
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
        //System.out.println(matcher.groupCount());

    }


}