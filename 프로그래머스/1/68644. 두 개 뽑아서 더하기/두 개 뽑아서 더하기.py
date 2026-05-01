from itertools import combinations

def solution(numbers):
    result = []
    temp = list(combinations(numbers, 2))
    for e in temp:
        result.append(sum(e))
        result = list(set(result))
    return sorted(result)