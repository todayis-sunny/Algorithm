from itertools import permutations

def solution(k, dungeons):
    ans = 0
    for p in permutations(dungeons, len(dungeons)):
        tmp = k
        cnt = 0 
        
        for need, spend in p:
            if tmp >= need:
                tmp -= spend
                cnt += 1
        ans = max(ans, cnt)
    return ans