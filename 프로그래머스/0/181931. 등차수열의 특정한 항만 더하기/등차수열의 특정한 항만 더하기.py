def solution(a, d, included):
    answer = 0
    value = a - d
    for i in range(len(included)):
        value += d
        if included[i]:
            answer += value
    return answer