def solution(cost, hint):
    n = len(cost)
    result = 1e9
    # 번들 조합 탐색
    for mask in range(1 << (n - 1)):
        hintsCount = [0] * (n + 1)
        bundleCost = 0
        
        # 몇번 번들을 구매했는지 탐색
        for i in range(n - 1):
            # 구매한 번들이 아닌 경우
            if (mask & (1 << i)) == 0:
                continue
            bundleCost += hint[i][0]
            for j in range(1, len(hint[i])):
                hintsCount[hint[i][j]] += 1
                
        # 총 비용 계산
        total = bundleCost
        for stage in range(n):
            use = min(hintsCount[stage + 1], n - 1)
            total += cost[stage][use]
        
        result = min(result, total)
    
    return result