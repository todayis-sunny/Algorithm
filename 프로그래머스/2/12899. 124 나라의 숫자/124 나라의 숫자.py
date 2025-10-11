def solution(n):
    result = []
    
    while n:
        k = n % 3
        if not k:
            k = 4
            n -= 1
        result.append(str(k))
        n //= 3
        
    return "".join(result[::-1])