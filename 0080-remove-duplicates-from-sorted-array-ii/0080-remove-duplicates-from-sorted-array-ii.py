class Solution:
    def removeDuplicates(self, nums: list[int]) -> int:
        if len(nums) <= 2:
            return len(nums)
        writer = 2
        
        for reader in range(2, len(nums)):
            if nums[reader] != nums[writer - 2]:
                nums[writer] = nums[reader]
                writer += 1
                
        return writer