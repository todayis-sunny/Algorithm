def solution(targets):
    result = 0
    targets.sort(key = lambda x: x[1])
    data = 0
    for target in targets:
        if target[0] >= data:
            result += 1
            data = target[1]
    return result