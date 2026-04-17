def solution(targets):
    result = 0
    targets.sort(key = lambda x: x[1])
    e = 0
    for target in targets:
        if target[0] >= e:
            result += 1
            e = target[1]
    return result