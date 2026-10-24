class Solution {
    private static final int[] DAYS_BEFORE_MONTH = {
        0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334
    };

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        String arrive = arriveAlice.compareTo(arriveBob) > 0 ? arriveAlice : arriveBob;
        String leave = leaveAlice.compareTo(leaveBob) < 0 ? leaveAlice : leaveBob;
        if (arrive.compareTo(leave) > 0) {
            return 0;
        }
        return dateToDayOfYear(leave) - dateToDayOfYear(arrive) + 1;
    }

    private int dateToDayOfYear(String date) {
        int month = (date.charAt(0) - '0') * 10 + (date.charAt(1) - '0');
        int day = (date.charAt(3) - '0') * 10 + (date.charAt(4) - '0');
        
        return DAYS_BEFORE_MONTH[month - 1] + day;
    }
}