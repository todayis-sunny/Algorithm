def solution(s):
    result = []
    tmp = []
    for ch in s:
        if ch in tmp:
            for i in range(1, len(tmp)+1):
                if ch == tmp[-i]:
                    result.append(i)
                    break
            tmp.append(ch)
        else:
            result.append(-1)
            tmp.append(ch)
    return result