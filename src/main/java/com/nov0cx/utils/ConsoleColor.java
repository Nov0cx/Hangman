package com.nov0cx.utils;

public class ConsoleColor {
    public static final String AQUA = "\u001B[34m"; //%1
    public static final String BLACK = "\u001B[30m"; //%2
    public static final String WHITE = "\u001B[37m"; //%3
    public static final String RESET = "\u001B[37m"; // %4
    public static final String RED = "\u001B[31m"; // %5
    public static final String GREEN = "\u001B[32m"; //%6
    public static final String YELLOW = "\u001B[33m"; //%7
    public static final String CYAN = "\u001B[36m"; //%8
    public static final String PURPLE = "\u001B[35m"; //%9
    public static final String ITALIC = "\u001B[3m"; //%a
    public static final String UNDERLINE = "\u001B[4m"; //%b

    private static final Pair<String, String>[] keys = new Pair[]{
            new Pair("%1", AQUA), new Pair("%2", BLACK), new Pair("%3", WHITE),
            new Pair("%4", RESET), new Pair("%5", RED), new Pair("%6", GREEN), new Pair("%7", YELLOW),
            new Pair("%8", CYAN), new Pair("%9", PURPLE), new Pair("%a", ITALIC), new Pair("%b", UNDERLINE)
    };

    //that method text with %Code
    public static String format(String s) {
        for (Pair<String, String> pair : keys) {
                while (s.contains(pair.a)) {
                    s = s.replace(pair.a, pair.b);
                }
        }
        return s;
    }

}
