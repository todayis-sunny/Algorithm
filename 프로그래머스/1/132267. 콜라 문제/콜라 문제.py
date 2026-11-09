def solution(a, b, n):
    result = 0
    # 마트에 줘야하는 병이 있을때까지 계속
    while a <= n:
        d, m = divmod(n, a)
        result += b * d
        n = b * d + m
    return result