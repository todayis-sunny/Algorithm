INF = 1e9

def solution(depth, money, excavate):
    w = len(depth)
    depth = [0] + depth
    # dp 배열
    dp = [[0] * (w + 2) for _ in range(w + 2)]
    # 선택 배열
    pick = [[0] * (w + 2) for _ in range(w + 2)]
    
    # 1. 구간별 최적 굴착 위치 계산
    for length in range(1, w + 1):
        for l in range(1, w - length + 2):
            r = l + (length - 1)
            dp[l][r] = INF
            # a. 하나씩 굴착해보기
            for k in range(l, r + 1):
                lCost = dp[l][k - 1] if l <= k - 1 else 0
                rCost = dp[k + 1][r] if r >= k + 1 else 0
                total = depth[k] + max(lCost, rCost)
                # 최적값 갱신
                if total < dp[l][r]:
                    dp[l][r] = total
                    pick[l][r] = k
    
    # 2. 최적 전략대로 실제 굴착 실행
    left, right = 1, w
    while left <= right:
        k = pick[left][right]
        result = excavate(k)

        # 보물 발견
        if result == 0:
            return k
        # 보물 좌측
        elif result == -1:
            right = k - 1
        # 보물 우측
        else:
            left = k + 1
        
    return 0