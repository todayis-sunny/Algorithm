def solution(s):
    result = []
    data = []
    for ch in s:
        if ch in data:
            for i in range(1, len(data) + 1):
                if ch == data[-i]:
                    result.append(i)
                    break
            data.append(ch)
        else:
            result.append(-1)
            data.append(ch)
    return result