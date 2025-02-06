from itertools import combinations

def solution(number):
    arr = list(combinations(number, 3))
    ans = 0
    for element in arr:
        if sum(element) == 0:
            ans += 1
    return ans