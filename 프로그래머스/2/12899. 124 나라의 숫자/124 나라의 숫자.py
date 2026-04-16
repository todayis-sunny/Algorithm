def solution(n):
    result = []
    while n:
        t = n % 3
        if not t:
            t = 4
            n -= 1
        result.append(str(t))
        n //= 3
    return ''.join(result[::-1])