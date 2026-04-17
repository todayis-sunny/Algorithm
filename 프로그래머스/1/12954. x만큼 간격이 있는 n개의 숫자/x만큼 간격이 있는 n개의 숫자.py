def solution(x, n):
    result = []
    if x == 0:
        for i in range(n):
            result.append(0)
        return result
    for i in range(x, x * (n + 1), x):
        result.append(i)
    return result