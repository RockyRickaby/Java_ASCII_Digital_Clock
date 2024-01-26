package clock;
/**
 * Formatter. This class generates an ASCII representation of a 
 * digital clock.
 */
public class ToClock {
    private static final String[][] NUMS = {{"#####", "#   #", "#   #", "#   #", "#####"},
                                            {"  #  ", " ##  ", "  #  ", "  #  ", "#####"},
                                            {"#####", "    #", "#####", "#    ", "#####"},
                                            {"#####", "    #", "#####", "    #", "#####"},
                                            {"#   #", "#   #", "#####", "    #", "    #"},
                                            {"#####", "#    ", "#####", "    #", "#####"},
                                            {"#####", "#    ", "#####", "#   #", "#####"},
                                            {"#####", "   # ", "  #  ", " #   ", "#    "},
                                            {"#####", "#   #", "#####", "#   #", "#####"},
                                            {"#####", "#   #", "#####", "    #", "#####"}};

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
     * @param hours hours
     * @param minutes minutes
     * @param seconds seconds.
     * @param h24 12 for 12 hour clock. 24 for 24 hour clock.
     * @return the generated ASCII clock as a String.
     */
    protected static String getTimeAsString(int hours, int minutes, int seconds, int h24) {
        int ampmIdx = 0;
        if (h24 == 12) {
            int auxHour = hours % 12;
            if (auxHour == 0) {
                auxHour = 12;
            }
            if (hours >= 12) {
                ampmIdx = 1;
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
            if (h24 == 12) {
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
