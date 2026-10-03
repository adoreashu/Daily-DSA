class Solution:
    def removeElement(self, nums: list[int], val: int) -> int:
        writer_hand = 0
        
        for reader_hand in range(len(nums)):
            if nums[reader_hand] != val:
                nums[writer_hand] = nums[reader_hand]
                writer_hand += 1
                
        return writer_hand