def solution(s):
    answer = []
    tmp = []
    for ch in s:
        if ch in tmp:
            for i in range(1, len(tmp)+1):
                if ch == tmp[-i]:
                    answer.append(i)
                    break
            tmp.append(ch)
        else:
            answer.append(-1)
            tmp.append(ch)
    return answer