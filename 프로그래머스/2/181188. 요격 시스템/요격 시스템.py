def solution(targets):
    answer = 0
    targets.sort(key=lambda x: x[1])
    e = 0
    for tg in targets:
        if tg[0] >= e:
            answer += 1
            e = tg[1]
    return answer