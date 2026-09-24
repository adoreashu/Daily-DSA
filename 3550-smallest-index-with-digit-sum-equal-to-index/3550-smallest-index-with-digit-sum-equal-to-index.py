class Solution:
    def smallestIndex(self, nums):
        for i in range(len(nums)):
            digitSum = 0
            temp = nums[i]

            while temp > 0:
                digitSum += temp % 10
                temp //= 10

            if digitSum == i:
                return i

        return -1