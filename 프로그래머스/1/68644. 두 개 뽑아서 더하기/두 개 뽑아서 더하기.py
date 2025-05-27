from itertools import combinations

def solution(numbers):
    ans = []
    temp = list(combinations(numbers, 2))
    for e in temp:
        ans.append(sum(e))
        ans = list(set(ans))
    return sorted(ans)