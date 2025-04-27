def solution(name, yearning, photo):
    ans = []
    for p in photo:
        score = 0
        for n in p:
            if n in name:
                score += yearning[name.index(n)]
        ans.append(score)

    return ans
