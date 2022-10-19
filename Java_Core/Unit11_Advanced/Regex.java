package Unit11_Advanced;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegexPatternExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("bang");
        String text = "1 + 1 bang 2";
        Matcher matcher = pattern.matcher(text);
        int start = 0;
        while (matcher.find(start)) {
            start = matcher.start();
            int end = matcher.end();
            System.out.println("numer: "+text.substring(start,end));
            start = end;
        }

//        System.out.println(matcher.find());
//        System.out.println("start = "+matcher.start()+" -end "+matcher.end());
//        System.out.println("value = "+text.substring(matcher.start(),matcher.end()));

    }
}