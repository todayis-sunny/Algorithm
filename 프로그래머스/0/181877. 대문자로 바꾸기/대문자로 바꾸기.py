def solution(myString):
    answer = ''
    for s in myString:
        if s.islower():
            answer += s.upper()
        else:
            answer += s

    return answer