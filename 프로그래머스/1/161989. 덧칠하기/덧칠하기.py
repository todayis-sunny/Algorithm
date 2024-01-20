def solution(n, m, section):
    curP = 0
    answer = 0
    for s in section:
        if curP < s:
            curP = s + m-1
            answer += 1
        else:
            continue
    return answer