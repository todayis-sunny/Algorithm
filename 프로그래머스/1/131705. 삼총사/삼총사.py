from itertools import combinations as combi

def solution(number):
    info = list(combi(number, 3))
    result = 0
    for e in info:
        if sum(e) == 0:
            result += 1
    return result