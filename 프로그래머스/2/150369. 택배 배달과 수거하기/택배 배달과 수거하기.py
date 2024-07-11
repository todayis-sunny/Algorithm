def solution(cap, n, deliveries, pickups):
    answer = 0
    flag = True
    dStart = n-1
    pStart = n-1
    while flag:
        
        dIdx = -1
        dCnt = cap
        for i in range(dStart, -1, -1):
            if deliveries[i]:
                if dIdx == -1:
                    dIdx = i
            if deliveries[i] >= dCnt:
                deliveries[i] -= dCnt
                break
            else:
                dCnt -= deliveries[i]
                deliveries[i] = 0
        
        pIdx = -1
        pCnt = cap
        for i in range(pStart, -1, -1):
            if pickups[i]:
                if pIdx == -1:
                    pIdx = i
            if pickups[i] >= pCnt:
                pickups[i] -= pCnt
                break
            else:
                pCnt -= pickups[i]
                pickups[i] = 0
        
        dStart = dIdx
        pStart = pIdx
        tmp = max(dIdx, pIdx)
        if tmp == -1:
            flag = False
        answer += (tmp + 1) * 2
        
    return answer