def solution(left, right):
    res = 0
    for num in range(left, right+1):
        temp = 0
        for n in range(1, num+1):
            if num % n == 0:
                temp += 1
        if temp % 2 == 0:
            res += num
        else:
            res -= num
    return res