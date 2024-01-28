package clock;
/**
 * Formatter. This class generates an ASCII representation of a 
 * digital clock.
 */
public class ToClock {
    private static final String[][] NUMS = {{"#####", "#   #", "#   #", "#   #", "#####"}, // 0
                                            {"  #  ", " ##  ", "  #  ", "  #  ", "#####"}, // 1
                                            {"#####", "    #", "#####", "#    ", "#####"}, // 2
                                            {"#####", "    #", "#####", "    #", "#####"}, // 3
                                            {"#   #", "#   #", "#####", "    #", "    #"}, // 4
                                            {"#####", "#    ", "#####", "    #", "#####"}, // 5
                                            {"#####", "#    ", "#####", "#   #", "#####"}, // 6
                                            {"#####", "   # ", "  #  ", " #   ", "#    "}, // 7
                                            {"#####", "#   #", "#####", "#   #", "#####"}, // 8
                                            {"#####", "#   #", "#####", "    #", "#####"}}; // 9

    private static final String[][] APM = {{"#####", "#   #", "#####", "#   #", "#   #"}, // A
                                           {"#####", "#   #", "#####", "#    ", "#    "}, // p
                                           {"#   #", "## ##", "# # #", "#   #", "#   #"}}; // M

    private static final String[] DOTS = {"     ", "  #  ", "     ", "  #  ", "     "};
    private static final int CHAR_LEN = 5;

    /**
     * No need for creating instances of this class.
     */
    private ToClock() {
    }

    /**
     * Returns an ASCII clock (as a String) with the values of {@code hours} as
     * the clock's hours, {@code minutes} as the clock's minutes
     * and {@code seconds} as the clock's seconds.
     * <p>
     * If {@code seconds} is less than or
     * equal to -1, seconds will be omitted.
     * @param hours
     * @param minutes
     * @param seconds
     * @param h24 {@code true} for a 24-hour clock. {@code false} for a 12-hour clock.
     * @return the generated ASCII clock as a String.
     */
    protected static String getTimeAsString(int hours, int minutes, int seconds, boolean h24) {
        if (hours < 0 || minutes < 0) {
            throw new IllegalArgumentException("Hours and/or minutes cannot be negative.");
        }
        int ampmIdx = (hours % 24) / 12;
        if (!h24) {
            int auxHour = hours % 12;
            if (auxHour == 0) {
                auxHour = 12;
            }
            hours = auxHour;
        }

        String[] hourDec = NUMS[hours / 10];
        String[] hourUnit = NUMS[hours % 10];

        String[] minDec = NUMS[minutes / 10];
        String[] minUnit = NUMS[minutes % 10];

        String[] secDec = null;
        String[] secUnit = null;

        if (seconds > -1) {
            secDec = NUMS[seconds / 10];
            secUnit = NUMS[seconds % 10];
        }
 
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < CHAR_LEN; i++) {
            str.append(hourDec[i])
               .append("  ")
               .append(hourUnit[i])
               .append(DOTS[i])
               .append(minDec[i])
               .append("  ")
               .append(minUnit[i]);
            if (seconds > -1) {
                str.append(DOTS[i])
                   .append(secDec[i])
                   .append("  ")
                   .append(secUnit[i]);
            }
            if (!h24) {
                str.append("      ")
                   .append(APM[ampmIdx][i])
                   .append("  ")
                   .append(APM[2][i]);
            }
            str.append("\n");
        }
        return str.toString();
    }
    /**
     * #####
     * #   #
     * #   #
     * #   #
     * #####
     * ------
     *   #
     *  ##
     *   #
     *   #
     * #####
     * ------
     * #####
     *     #
     * #####
     * #
     * #####
     * ------
     * #####
     *     #
     * #####
     *     #
     * #####
     * ------
     * #   #
     * #   #
     * #####
     *     #
     *     #
     * ------
     * #####
     * #
     * #####
     *     #
     * #####
     * ------
     * #####
     * #
     * #####
     * #   #
     * #####
     * ------
     * #####
     *    #
     *   #
     *  #
     * #
     * ------
     * #####
     * #   #
     * #####
     * #   #
     * #####
     * ------
     * #####
     * #   #
     * #####
     *     #
     * #####
     * ------
     * #####
     * #   #
     * #####
     * # 
     * #
     * ------
     * #####
     * #   #
     * #####
     * #   #
     * #   #
     * ------
     * #   #
     * ## ##
     * # # #
     * #   #
     * #   #
     * ------
     * 
     *   #  
     * 
     *   #
     * 
     * ------
     */
}
