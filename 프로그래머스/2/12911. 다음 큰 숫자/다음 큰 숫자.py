def solution(n):
    num = n
    ans = 0
    
    while True:
        num += 1
        if bin(n)[2:].count('1') == bin(num)[2:].count('1'):
            ans = num
            break
    
    return ans