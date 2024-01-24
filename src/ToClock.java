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

    public static String getTimeAsString(long hours, long minutes, int h24) {
        int ampmIdx = 0;
        if (h24 == 12) {
            long auxHour = hours % h24;
            if (auxHour == 0) {
                auxHour = 12;
            }
            if (hours >= 12) {
                ampmIdx = 1;
            }
            hours = auxHour;
        }

        String[] hourDec = NUMS[(int) hours / 10].split("\n");
        String[] hourUnit = NUMS[(int) hours % 10].split("\n");

        String[] minDec = NUMS[(int) minutes / 10].split("\n");
        String[] minUnit = NUMS[(int) minutes % 10].split("\n");

        String[] theAorP = AMPM[ampmIdx].split("\n");
        String[] theM = AMPM[2].split("\n");

        String[] dots = DOTS.split("\n");

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < CHAR_LEN; i++) {
            str.append(hourDec[i])
               .append("  ")
               .append(hourUnit[i])
               //.append("  ")
               .append(dots[i])
               //.append("  ")
               .append(minDec[i])
               .append("  ")
               .append(minUnit[i]);
               //.append("\n");
            if (h24 == 12) {
                str.append("     ")
                   .append(theAorP[i])
                   .append("  ")
                   .append(theM[i]);
            }
            str.append("\n");
        }
        return str.toString();
    }

    public static String getTimeAsString(long hours, long minutes, long seconds, int h24) {
        int ampmIdx = 0;
        if (h24 == 12) {
            long auxHour = hours % h24;
            if (auxHour == 0) {
                auxHour = 12;
            }
            if (hours >= 12) {
                ampmIdx = 1;
            }
            hours = auxHour;
        }

        String[] hourDec = NUMS[(int) hours / 10].split("\n");
        String[] hourUnit = NUMS[(int) hours % 10].split("\n");

        String[] minDec = NUMS[(int) minutes / 10].split("\n");
        String[] minUnit = NUMS[(int) minutes % 10].split("\n");

        String[] secDec = NUMS[(int) seconds / 10].split("\n");
        String[] secUnit = NUMS[(int) seconds % 10].split("\n");

        String[] theAorP = AMPM[ampmIdx].split("\n");
        String[] theM = NUMS[10].split("\n");

        String[] dots = DOTS.split("\n");

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < CHAR_LEN; i++) {
            str.append(hourDec[i])
               .append("  ")
               .append(hourUnit[i])
               //.append("  ")
               .append(dots[i])
               //.append("  ")
               .append(minDec[i])
               .append("  ")
               .append(minUnit[i])
               //.append("  ")
               .append(dots[i])
               //.append("  ")
               .append(secDec[i])
               .append("  ")
               .append(secUnit[i]);
                //.append("\n");
            if (h24 == 12) {
                str.append("     ")
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
     */
}
