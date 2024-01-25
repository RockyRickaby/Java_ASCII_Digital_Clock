package clock;
public class ToClock {
    private static final String[] NUMS = {"#####\n#   #\n#   #\n#   #\n#####", // 0
                                          "  #  \n ##  \n  #  \n  #  \n#####", // 1
                                          "#####\n    #\n#####\n#    \n#####", // 2
                                          "#####\n    #\n#####\n    #\n#####", // 3
                                          "#   #\n#   #\n#####\n    #\n    #", // 4
                                          "#####\n#    \n#####\n    #\n#####", // 5
                                          "#####\n#    \n#####\n#   #\n#####", // 6
                                          "#####\n   # \n  #  \n #   \n#    ", // 7
                                          "#####\n#   #\n#####\n#   #\n#####", // 8
                                          "#####\n#   #\n#####\n    #\n#####"}; // 9

    private static final String[] AMPM = {"#####\n#   #\n#####\n#   #\n#   #", // A
                                          "#####\n#   #\n#####\n#    \n#    ", // P
                                          "#   #\n## ##\n# # #\n#   #\n#   #"}; // M

    private static final String DOTS = "     \n  #  \n     \n  #  \n     ";
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

        String[] hourDec = NUMS[hours / 10].split("\n");
        String[] hourUnit = NUMS[hours % 10].split("\n");

        String[] minDec = NUMS[minutes / 10].split("\n");
        String[] minUnit = NUMS[minutes % 10].split("\n");

        String[] secDec = null;
        String[] secUnit = null;

        String[] theAorP = null;
        String[] theM = null;

        if (seconds > -1) {
            secDec = NUMS[seconds / 10].split("\n");
            secUnit = NUMS[seconds % 10].split("\n");
        }
        if (h24 == 12) {
            theAorP = AMPM[ampmIdx].split("\n");
            theM = AMPM[2].split("\n");
        }
 
        String[] dots = DOTS.split("\n");

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < CHAR_LEN; i++) {
            str.append(hourDec[i])
               .append("  ")
               .append(hourUnit[i])
               .append(dots[i])
               .append(minDec[i])
               .append("  ")
               .append(minUnit[i]);
            if (seconds > -1) {
                str.append(dots[i])
                   .append(secDec[i])
                   .append("  ")
                   .append(secUnit[i]);
            }
            if (h24 == 12) {
                str.append("      ")
                   .append(theAorP[i])
                   .append("  ")
                   .append(theM[i]);
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
