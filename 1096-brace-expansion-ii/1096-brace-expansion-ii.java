import java.util.*;

class Solution {
    private int idx = 0;

    public List<String> braceExpansionII(String expression) {
        char[] exp = expression.toCharArray();
        
        Set<String> resultSet = parse(exp);
                List<String> sortedList = new ArrayList<>(resultSet);
        Collections.sort(sortedList);
        
        return sortedList;
    }

    private Set<String> parse(char[] exp) {
        Set<String> res = new HashSet<>();
        Set<String> curr = new HashSet<>();
        curr.add(""); 

        while (idx < exp.length) {
            char c = exp[idx++];

            if (c == '{') {
                Set<String> nested = parse(exp);
                curr = cartesianProduct(curr, nested);
            } else if (c == '}') {
                break;
            } else if (c == ',') {
                res.addAll(curr);
                curr = new HashSet<>();
                curr.add("");
            } else {
                Set<String> nextCurr = new HashSet<>();
                for (String s : curr) {
                    nextCurr.add(s + c);
                }
                curr = nextCurr;
            }
        }
        res.addAll(curr);
        return res;
    }

    private Set<String> cartesianProduct(Set<String> s1, Set<String> s2) {
        Set<String> res = new HashSet<>(s1.size() * s2.size());
        for (String a : s1) {
            for (String b : s2) {
                res.add(a + b);
            }
        }
        return res;
    }
}