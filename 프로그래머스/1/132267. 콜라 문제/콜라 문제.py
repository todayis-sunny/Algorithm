def solution(a, b, n):
    ans = 0
    # 마트에 줘야하는 병이 있을때까지 계속
    while a <= n:
        div, mod = divmod(n, a)
        ans += b * div
        n = b * div + mod
    return ans