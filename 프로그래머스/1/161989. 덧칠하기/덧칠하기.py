def solution(n, m, section):
    curr = 0
    result = 0
    for s in section:
        if curr < s:
            curr = s + (m - 1)
            result += 1
        else:
            continue
    return result