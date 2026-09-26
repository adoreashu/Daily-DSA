import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> dict = new HashMap<>(knowledge.size());
        for (List<String> pair : knowledge) {
            dict.put(pair.get(0), pair.get(1));
        }

        char[] chars = s.toCharArray();
        StringBuilder res = new StringBuilder(chars.length);
        
        int i = 0;
        int n = chars.length;
        
        while (i < n) {
            if (chars[i] == '(') {
                i++; 
                int keyStart = i;
                
                while (chars[i] != ')') {
                    i++;
                }
                
                String key = new String(chars, keyStart, i - keyStart);
                res.append(dict.getOrDefault(key, "?"));
            } else {
                res.append(chars[i]);
            }
            i++;
        }
        
        return res.toString();
    }
}