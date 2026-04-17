def solution(n):
    ans = "수박" * (n // 2)
    if n % 2 == 1:
        ans += "수"
    return ans