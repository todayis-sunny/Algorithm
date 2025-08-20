from itertools import combinations

def solution(number):
    arr = list(combinations(number, 3))
    ans = 0
    for el in arr:
        if sum(el) == 0:
            ans += 1
    return ans