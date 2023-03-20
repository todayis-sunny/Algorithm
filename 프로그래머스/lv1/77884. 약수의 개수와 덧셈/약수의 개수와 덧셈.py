def solution(left, right):
    answer = 0
    for num in range(left, right+1):
        temp = 0
        for n in range(1, num+1):
            if num % n == 0:
                temp += 1
        if temp % 2 == 0:
            answer += num
        else:
            answer -= num
    return answer