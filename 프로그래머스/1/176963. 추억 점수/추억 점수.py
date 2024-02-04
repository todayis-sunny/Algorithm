def solution(name, yearning, photo):
    answer = []

    for ppl in photo:
        score = 0
        for n in ppl:
            if n in name:
                score += yearning[name.index(n)]
        answer.append(score)

    return answer
