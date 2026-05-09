def solution(name, yearning, photo):
    result = []
    for ph in photo:
        score = 0
        for p in ph:
            if p in name:
                score += yearning[name.index(p)]
        result.append(score)

    return result
