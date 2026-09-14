def solution(n, s):
    if n > s:
        return [-1]
    result = []
    tmp = s // n
    cnt = s - tmp * n
    for _ in range(n - cnt):
        result.append(tmp)
    for _ in range(cnt):
        result.append(tmp + 1)
    return result