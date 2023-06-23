def solution(scores):
    answer = 1
    wh = scores[0]
    wh_sco = sum(wh)
    scores.sort(key = lambda x:(-x[0], x[1]))
    max_sco = 0
    for i in range(len(scores)):
        total = sum(scores[i])
        if wh[0] < scores[i][0] and wh[1] < scores[i][1]:
            return -1
        
        if scores[i][1] >= max_sco:
            max_sco = scores[i][1]
            if scores[i][0] + scores[i][1] > wh_sco:
                answer += 1

    return answer