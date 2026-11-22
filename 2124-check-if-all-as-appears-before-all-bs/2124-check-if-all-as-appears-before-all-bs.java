class Solution {
    public boolean checkString(String s) {
        int firstB = s.indexOf('b');
        return firstB == -1 || s.indexOf('a', firstB + 1) == -1;
    }
}