from collections import Counter

def solution(X, Y):
    nums = Counter(X) & Counter(Y)
    if not nums:
        return "-1"
    elif list(nums) == ["0"]:
        return "0"
    
    nums_order = sorted(list(nums), reverse = True)
    answer = ''
    for num in nums_order:
        answer += num * nums[num]
    return answer