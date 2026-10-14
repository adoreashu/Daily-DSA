class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > num * 9) {
            return "";
        }
        
        int count9 = sum / 9;
        int rem = sum % 9;
                char[] res = new char[num];
        int idx = 0;
                while (count9-- > 0) {
            res[idx++] = '9';
        }
                if (rem > 0) {
            res[idx++] = (char) ('0' + rem);
        }
        
        // 3. Fill the rest of the lower place values with '0's
        while (idx < num) {
            res[idx++] = '0';
        }
        
        return new String(res);
    }
}