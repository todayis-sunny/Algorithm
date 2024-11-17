def solution(name, yearning, photo):
    answer = []

    for p in photo:
        score = 0
        for n in p:
            if n in name:
                score += yearning[name.index(n)]
        answer.append(score)

    return answer
