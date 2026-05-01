def solution(s):
    result = []
    temp = []
    for ch in s:
        if ch in temp:
            for i in range(1, len(temp) + 1):
                if ch == temp[-i]:
                    result.append(i)
                    break
            temp.append(ch)
        else:
            result.append(-1)
            temp.append(ch)
    return result