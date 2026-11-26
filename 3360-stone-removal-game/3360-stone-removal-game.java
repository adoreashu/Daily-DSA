class Solution {
    private static final long WIN_MASK = 0x0006_1F03_F807_FC00L;

    public boolean canAliceWin(int n) {
        return ((WIN_MASK >>> n) & 1L) != 0;
    }
}