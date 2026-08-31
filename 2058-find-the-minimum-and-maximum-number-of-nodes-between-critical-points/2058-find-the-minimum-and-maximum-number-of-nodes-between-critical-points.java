
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int minDistance = Integer.MAX_VALUE;
        int firstCritIdx = -1;
        int prevCritIdx = -1;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int currentIndex = 1; 
        
        while (curr.next != null) {
            ListNode next = curr.next;
            
            if ((curr.val > prev.val && curr.val > next.val) || 
                (curr.val < prev.val && curr.val < next.val)) {
                
                if (firstCritIdx == -1) {
                    firstCritIdx = currentIndex;
                } else {
                    minDistance = Math.min(minDistance, currentIndex - prevCritIdx);
                }
                prevCritIdx = currentIndex;
            }
                        prev = curr;
            curr = next;
            currentIndex++;
        }
        
        if (firstCritIdx == prevCritIdx) {
            return new int[]{-1, -1};
        }
        
        return new int[]{minDistance, prevCritIdx - firstCritIdx};
    }
}