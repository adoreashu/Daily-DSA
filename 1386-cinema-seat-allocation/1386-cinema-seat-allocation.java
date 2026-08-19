import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowToMask = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            rowToMask.put(row, rowToMask.getOrDefault(row, 0) | (1 << col));
        }
        
        int maxGroups = n * 2;
        
        for (int reservedMask : rowToMask.values()) {
            maxGroups -= 2;
            
            boolean leftFree = (reservedMask & 60) == 0;    
            boolean rightFree = (reservedMask & 960) == 0;  
            boolean middleFree = (reservedMask & 240) == 0; 
            
            if (leftFree && rightFree) {
                maxGroups += 2;
            } else if (leftFree || rightFree || middleFree) {
                maxGroups += 1;
            }
        }
        
        return maxGroups;
    }
}