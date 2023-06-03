def solution(weights):
    dic = {}
    for w in weights:
        if w not in dic:
            dic[w] = 1
        else:
            dic[w] += 1
    dic = sorted(dic.items())
    ans = 0
    for i in range(len(dic)):
        if dic[i][1] > 1:
            ans += (dic[i][1] * (dic[i][1] -1)) / 2
        for j in range(i, len(dic)):
            if dic[j][0] > 2 * dic[i][0]:
                break
            if 4*dic[i][0] == 2*dic[j][0]:
                ans += dic[i][1] * dic[j][1]
            elif 3*dic[i][0] == 2*dic[j][0]:
                ans += dic[i][1] * dic[j][1]
            elif 4*dic[i][0] == 3*dic[j][0]:
                ans += dic[i][1] * dic[j][1]
    return ans