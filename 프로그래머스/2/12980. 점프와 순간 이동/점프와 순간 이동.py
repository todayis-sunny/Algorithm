def solution(n):
    answer = 0
    while n > 0:
        if n % 2 == 0: # 짝수이면 //2
            n = n // 2
        else: # 홀수이면 -1
            n = n - 1
            answer += 1 # -1 하는 횟수가 답
    
    return answer