from collections import Counter

def solution(X, Y):
    nums = Counter(X) & Counter(Y)
    if not nums:
        return "-1"
    elif list(nums) == ["0"]:
        return "0"
    
    numsOrder = sorted(list(nums), reverse = True)
    answer = ''
    for num in numsOrder:
        answer += num * nums[num]
    return answer