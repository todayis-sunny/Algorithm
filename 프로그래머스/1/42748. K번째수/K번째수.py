def solution(array, commands):
    result = []
    for e in commands:
        temp = array[e[0] - 1 : e[1]]
        temp = sorted(temp)
        result.append(temp[e[2] - 1])
    return result