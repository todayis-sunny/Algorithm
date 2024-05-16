def solution(myString):
    num = 0
    answer = []
    for s in myString:
        if s != "x":
            num += 1
        else:
            answer.append(num)
            num = 0
    answer.append(num)
    return answer