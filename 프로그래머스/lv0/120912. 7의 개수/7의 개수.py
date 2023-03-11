def solution(array):
    answer = ''
    for i in array:
        answer += str(i)
    return answer.count('7')