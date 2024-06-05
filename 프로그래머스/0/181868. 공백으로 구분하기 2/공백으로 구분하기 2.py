def solution(my_string):
    answer = my_string.split()
    while "" in answer:
        answer.remove("")
    return answer