def solution(answers):
    answer = []
    
    length = len(answers)
    
    a = [1, 2, 3, 4, 5]
    b = [2, 1, 2, 3, 2, 4, 2, 5]
    c = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    
    # a, b, c의 득점
    scores = [0] * 3
    
    for i in range(length):
        if (a[i % 5]) == answers[i]:
            scores[0] += 1
        if (b[i % 8]) == answers[i]:
            scores[1] += 1
        if (c[i % 10]) == answers[i]:
            scores[2] += 1
    
    max_value = max(scores)
    
    for i in range(len(scores)):
        if scores[i] == max_value:
            answer.append(i + 1)
    
    return answer