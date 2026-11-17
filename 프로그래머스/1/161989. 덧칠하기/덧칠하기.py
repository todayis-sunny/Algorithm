def solution(n, m, section):
    cur = 0
    result = 0
    for s in section:
        if cur < s:
            cur = s + (m - 1)
            result += 1
        else:
            continue
    return result