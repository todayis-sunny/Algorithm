def solution(scores):
    answer = 1
    wh = scores[0]
    whSco = sum(wh)
    scores.sort(key = lambda x:(-x[0], x[1]))
    maxSco = 0
    for i in range(len(scores)):
        total = sum(scores[i])
        if wh[0] < scores[i][0] and wh[1] < scores[i][1]:
            return -1
        
        if scores[i][1] >= maxSco:
            maxSco = scores[i][1]
            if scores[i][0] + scores[i][1] > whSco:
                answer += 1

    return answer