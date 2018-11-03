package utils;

public final class ConsoleColor {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public enum Color {
        ANSI_RESET ("\u001B[0m"),
        ANSI_BLACK ("\u001B[30m"),
        ANSI_RED ("\u001B[31m"),
        ANSI_GREEN ("\u001B[32m"),
        ANSI_YELLOW ("\u001B[33m"),
        ANSI_BLUE ("\u001B[34m"),
        ANSI_PURPLE ("\u001B[35m"),
        ANSI_CYAN ("\u001B[36m"),
        ANSI_WHITE ("\u001B[37m");

        private String value;

        Color (String value) { this.value = value; }

        public String getValue() {return value;}
    }

    public static final String setColor(String str, String color) { return color + str + ANSI_RESET; }

    public static final String setColor(String str, Color color) { return color.value + str + Color.ANSI_RESET.getValue(); }
}
