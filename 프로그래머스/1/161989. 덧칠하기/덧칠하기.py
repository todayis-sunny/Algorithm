def solution(n, m, section):
    curr = 0
    answer = 0
    for s in section:
        if curr < s:
            curr = s + m - 1
            answer += 1
        else:
            continue
    return answer