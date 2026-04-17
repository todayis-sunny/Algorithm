def solution(num):
    answer = 0
    while num != 1:
        # 짝수
        if num % 2 == 0:
            num /= 2
        # 홀수
        else:
            num = num * 3 + 1
            
        answer += 1
        if answer == 500:
            return -1
        
    return answer