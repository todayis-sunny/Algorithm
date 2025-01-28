def solution(k, tangerine):
    cnt = k
    dic = {}
    ans = 0
    for t in tangerine:
        if t not in dic:
            dic[t] = 1
        else:
            dic[t] += 1
    dic = sorted(dic.items(), key = lambda x : x[1], reverse = True)
    for i in range(len(dic)):
        cnt -= dic[i][1]
        if cnt <= 0:
            ans = i+1
            break
    return ans