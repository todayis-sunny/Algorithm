def solution(s):
    result = []
    info = []
    for ch in s:
        if ch in info:
            for i in range(1, len(info) + 1):
                if ch == info[-i]:
                    result.append(i)
                    break
            info.append(ch)
        else:
            result.append(-1)
            info.append(ch)
    return result