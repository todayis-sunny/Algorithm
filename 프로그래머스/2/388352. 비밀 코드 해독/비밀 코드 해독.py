from itertools import combinations

def solution(n, q, ans):
    f = list(combinations(range(1, n + 1), 5))

    for g, cnt in zip(q, ans):
         f = [code for code in f if len(set(code) & set(g)) == cnt]

    return len(f)