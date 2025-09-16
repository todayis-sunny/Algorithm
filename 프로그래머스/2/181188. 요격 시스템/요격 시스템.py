def solution(targets):
    ans = 0
    targets.sort(key = lambda x: x[1])
    e = 0
    for tg in targets:
        if tg[0] >= e:
            ans += 1
            e = tg[1]
    return ans