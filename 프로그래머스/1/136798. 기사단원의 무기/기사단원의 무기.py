def solution(number, limit, power):
    ans = 0
    
    for i in range(1, number+1):
        temp = 0
        for j in range(1, int(i ** 0.5) + 1):
            if i % j == 0:
                if i != j*j:
                    temp += 2
                else:
                    temp += 1
            if temp > limit:
                temp = power
                break
        ans += temp
        
    return ans