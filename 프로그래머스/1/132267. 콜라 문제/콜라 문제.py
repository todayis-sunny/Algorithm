def solution(a, b, n):
    ans = 0
    while a <= n:
        div, mod = divmod(n, a)
        ans += b * div
        n = b * div + mod
    return ans