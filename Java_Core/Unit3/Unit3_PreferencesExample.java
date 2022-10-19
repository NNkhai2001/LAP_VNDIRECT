import java.util.prefs.Preferences;

class PreferencesExample {
    public static void main(String[] args) {
        Preferences preferences = Preferences.userNodeForPackage(PreferencesExample.class);
        System.out.println("Old Value= "+preferences.get("data1", "No Value"));
        preferences.put("data1", "value" +System.currentTimeMillis());
        System.out.println("New Value= "+preferences.get("data1", "No Value"));
    }
}