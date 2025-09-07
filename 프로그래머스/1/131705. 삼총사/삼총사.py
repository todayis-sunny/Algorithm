from itertools import combinations

def solution(number):
    arr = list(combinations(number, 3))
    ans = 0
    for e in arr:
        if sum(e) == 0:
            ans += 1
    return ans