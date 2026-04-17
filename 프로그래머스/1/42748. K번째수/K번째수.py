def solution(array, commands):
    answer = []
    for e in commands:
        temp = array[e[0] - 1 : e[1]]
        temp = sorted(temp)
        answer.append(temp[e[2] - 1])
    return answer