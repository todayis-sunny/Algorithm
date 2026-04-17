def solution(info, n, m):
    INF = 1e9
    dp = [[INF] * n for _ in range(len(info))] # [i][n] : i번째 물건을 훔칠때, A가 남긴 흔적의 갯수 -> B가 남긴 흔적의 갯수
    # 초기화
    if info[0][0] < n:
        dp[0][info[0][0]] = 0 # A가 물건을 훔침
    if info[0][1] < m:
        dp[0][0] = info[0][1] # B가 물건을 훔침
    
    # 시뮬레이션
    for i in range(1, len(info)):
        nA = info[i][0] # A가 남길 흔적
        nB = info[i][1] # B가 남길 흔적
        for k in range(n-1, -1, -1):
            # 불가능한 경우 무시
            if dp[i - 1][k] == INF: 
                continue
            # A가 한도초과가 아닐 경우
            if k + nA < n:
                dp[i][k + nA] = min(dp[i][k + nA], dp[i - 1][k])
            # B가 한도 초과가 아닐 경우
            if dp[i - 1][k] + nB < m:
                dp[i][k] = min(dp[i][k], dp[i - 1][k] + nB)

    # 정답 반환                 
    for ans in range(n):
        if dp[-1][ans] != INF:
            return ans
    return -1



