from collections import Counter

def solution(X, Y):
    nums = Counter(X) & Counter(Y)
    if not nums:
        return "-1"
    elif list(nums) == ["0"]:
        return "0"
    
    numsOrder = sorted(list(nums), reverse = True)
    result = ""
    for num in numsOrder:
        result += num * nums[num]
    return result