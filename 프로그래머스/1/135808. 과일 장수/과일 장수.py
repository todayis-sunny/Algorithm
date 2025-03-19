def solution(k, m, score):
    
    if m > len(score):
        return 0
    answer = 0
    score = sorted(score, reverse = True)
    for i in range(1, len(score) // m + 1):
        answer += score[i * m - 1] * m
    return answer