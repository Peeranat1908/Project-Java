package ku.cs.models;

public class Theme {
    private static String currentStyleSheet = "dark-theme.css";  // Default stylesheet

    public static String getCurrentStyleSheet() {
        return currentStyleSheet;
    }

    public static void setCurrentStyleSheet(String newStyleSheet) {
        currentStyleSheet = newStyleSheet;
    }
}
