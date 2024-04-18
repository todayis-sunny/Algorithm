from itertools import combinations, product
from bisect import bisect_left

def solution(dice):
    dic = {}
    L = len(dice)
    half = L // 2
    for AIdxCombi in combinations(range(L), half):
        BIdxCombi = [b for b in range(L) if b not in AIdxCombi]
        A, B = [], []
        for orderProduct in product(range(6), repeat=half):
            A.append(sum(dice[i][j] for i, j in zip(AIdxCombi, orderProduct)))
            B.append(sum(dice[i][j] for i, j in zip(BIdxCombi, orderProduct)))
        B.sort()
        
        wins = sum(bisect_left(B, num) for num in A)
        dic[wins] = list(AIdxCombi)
    
    max_key = max(dic.keys())
    return [x+1 for x in dic[max_key]]