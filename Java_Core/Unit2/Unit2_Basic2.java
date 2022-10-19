/*
Coder:NNKhai
Date:06/09/2022
JSE.Unit01.Basic.2
*/
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.util.Calendar.DATE;
import java.util.Locale;

class SystemExample {

    public static void main(String[] args) {
        System.out.println("JAVA_HOME = " + System.getProperty("os.version"));
        System.setProperty("java.home", "C:\\Program Files\\Java\\jre1.8.0_202");
        System.out.println("JAVA_HOME = " + System.getProperty("java.home"));
        System.out.println("User = " + System.getProperty("user.name"));
    }
}

class DateTimeFormatExample {

    public static void main(String[] args) {
        Locale locale = new Locale("Vi", "VN");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE,dd MMMMM yyyy", locale);
        Calendar calendar = Calendar.getInstance();
        System.out.println("Datetime: " + dateFormat.format(calendar.getTime()));
    }
}

class StringSwitchStatement {

    public String getTypeOfDay(Calendar calendar) {
        String type = calendar.getDisplayName(Calendar.DAY_OF_WEEK,
                Calendar.SHORT, new Locale("Vi"));
        switch (type) {
            case "Th 2":
                return "Start of work week";
            case "Th 3":
                return "Tuesday";
            case "Th 4":
                return "Wednesday";
            case "Th 5":
                return "Thursday";
            case "Th 6":
                return "Friday";
            case "Th 7":
                return "Saturday";
            case "CN":
                return "Sunday";
        }
        return "Unknown";

    }

    public static void main(String[] args) {
        StringSwitchStatement statement = new StringSwitchStatement();
        Calendar calendar = Calendar.getInstance();
        System.out.println("Today is : " + statement.getTypeOfDay(calendar));
        calendar.set(Calendar.DATE, calendar.get(DATE) + 1);
        System.out.println("Tomorow is " + statement.getTypeOfDay(calendar));
    }
}
