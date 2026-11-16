class Solution {
    public String digitSum(String s, int k) {
        if (s.length() <= k) {
            return s;
        }
                char[] buf = s.toCharArray();
        int len = buf.length;
        
        while (len > k) {
            int writeIdx = 0;
                        for (int i = 0; i < len; i += k) {
                int sum = 0;
                int end = Math.min(i + k, len);
                
                for (int j = i; j < end; j++) {
                    sum += buf[j] - '0';
                }
                if (sum >= 100) {
                    buf[writeIdx++] = (char) ((sum / 100) + '0');
                    buf[writeIdx++] = (char) (((sum / 10) % 10) + '0');
                    buf[writeIdx++] = (char) ((sum % 10) + '0');
                } else if (sum >= 10) {
                    buf[writeIdx++] = (char) ((sum / 10) + '0');
                    buf[writeIdx++] = (char) ((sum % 10) + '0');
                } else {
                    buf[writeIdx++] = (char) (sum + '0');
                }
            }
                        len = writeIdx;
        }
                return new String(buf, 0, len);
    }
}