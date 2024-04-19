def solution(my_string, m, c):
    answer = my_string[c-1::m]
    return "".join(answer)