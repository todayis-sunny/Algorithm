def solution(n):
    ans = 0
    for i in range(1,n+1):
        if n % i == 0:
            ans += i
    return ans