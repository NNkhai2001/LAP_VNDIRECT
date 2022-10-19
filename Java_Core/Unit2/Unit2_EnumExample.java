
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Locale;

public class Unit2_EnumExample {

    public enum VnDay {
        THU_HAI, THU_BA, CHU_NHAT, THU_BAY, KHONG_BIET;

        static VnDay valueOf(Calendar calendar) {
            Locale locale = new Locale("Vi");
            String type = calendar.getDisplayName(Calendar.DAY_OF_WEEK,
                    Calendar.SHORT, locale);
            switch (type) {
                case "Th2":
                    return THU_HAI;
                case "Th 3":
                    return THU_BA;
                case "Th7":
                    return THU_BAY;
                case "CN":
                    return CHU_NHAT;
            }
            return KHONG_BIET;
        }
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        System.out.println("Hom nay la: " + VnDay.valueOf(calendar));
        // Locale locale = new Locale("Vi");
        // String type = calendar.getDisplayName(calendar.DAY_OF_WEEK,
        //       calendar.SHORT, locale);
        // System.out.println(type);
    }
}
class MessageFormatExample {
    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE, dd MMMM yyyy");
        String message = MessageFormat.format("Hello {0}! Today is {1}.", args[0],
                dateFormat.format(Calendar.getInstance().getTime()));
        System.out.println(message);
    }
}
