from itertools import combinations as combi

def solution(number):
    arr = list(combi(number, 3))
    result = 0
    for e in arr:
        if sum(e) == 0:
            result += 1
    return result