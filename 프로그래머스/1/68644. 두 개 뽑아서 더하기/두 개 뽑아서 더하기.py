from itertools import combinations

def solution(numbers):
    ans = []
    temp = list(combinations(numbers, 2))
    for element in temp:
        ans.append(sum(element))
        ans = list(set(ans))
    return sorted(ans)