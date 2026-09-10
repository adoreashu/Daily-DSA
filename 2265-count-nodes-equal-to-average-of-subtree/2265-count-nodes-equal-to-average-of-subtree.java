/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int matchCount = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return matchCount;
    }

    private long dfs(TreeNode node) {
        if (node == null) {
            return 0L;
        }
        
        long left = dfs(node.left);
        long right = dfs(node.right);
        
        long currentSum = (left >> 32) + (right >> 32) + node.val;
        
        long currentCount = (left & 0xFFFFFFFFL) + (right & 0xFFFFFFFFL) + 1;
        
        if (currentSum / currentCount == node.val) {
            matchCount++;
        }
        
        return (currentSum << 32) | currentCount;
    }
}