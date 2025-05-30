def solution(name, yearning, photo):
    ans = []
    for p in photo:
        score = 0
        for k in p:
            if k in name:
                score += yearning[name.index(k)]
        ans.append(score)

    return ans
