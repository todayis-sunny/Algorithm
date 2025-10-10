from itertools import combinations as combi

def solution(number):
    arr = list(combi(number, 3))
    ans = 0
    for e in arr:
        if sum(e) == 0:
            ans += 1
    return ans