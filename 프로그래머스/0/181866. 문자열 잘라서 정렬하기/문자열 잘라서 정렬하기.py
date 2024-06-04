def solution(myString):
    answer = sorted(myString.split("x"))
    while "" in answer:
        answer.remove("")
    return answer