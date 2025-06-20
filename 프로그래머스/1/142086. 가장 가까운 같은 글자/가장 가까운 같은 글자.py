def solution(s):
    ans = []
    tmp = []
    for ch in s:
        if ch in tmp:
            for i in range(1, len(tmp)+1):
                if ch == tmp[-i]:
                    ans.append(i)
                    break
            tmp.append(ch)
        else:
            ans.append(-1)
            tmp.append(ch)
    return ans