def solution(num):
    result = 0
    while num != 1:
        # 짝수
        if num % 2 == 0:
            num /= 2
        # 홀수
        else:
            num = num * 3 + 1
            
        result += 1
        if result == 500:
            return -1
        
    return result