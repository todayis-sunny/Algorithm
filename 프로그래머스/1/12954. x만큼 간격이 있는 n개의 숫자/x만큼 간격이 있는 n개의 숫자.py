def solution(x, n):
    answer = []
    if x == 0:
        for i in range(n):
            answer.append(0)
        return answer
    for i in range(x, x * (n + 1), x):
        answer.append(i)
    return answer