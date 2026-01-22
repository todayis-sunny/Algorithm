def solution(name, yearning, photo):
    result = []
    for p in photo:
        score = 0
        for k in p:
            if k in name:
                score += yearning[name.index(k)]
        result.append(score)

    return result
