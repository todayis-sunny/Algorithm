from collections import deque

INF = float('inf')

def solution(alp, cop, problems):
    # 1. 최종 목표 능력치 설정
    maxAlp = max([p[0] for p in problems])
    maxCop = max([p[1] for p in problems])
    
    # 시작 알고력과 코딩력이 목표치보다 높을 수 있으므로 최대치를 갱신
    if alp >= maxAlp and cop >= maxCop:
        return 0
    
    if alp > maxAlp:
        alp = maxAlp
    if cop > maxCop:
        cop = maxCop
    
    # 2. DP 테이블 초기화
    dp = [[INF] * (maxCop + 2) for _ in range(maxAlp + 2)]
    dp[alp][cop] = 0

    # 3. BFS 탐색 시작
    q = deque([(alp, cop)])
    
    while q:
        currAlp, currCop = q.popleft()
        
        # 3가지 행동: 알고력 공부, 코딩력 공부, 문제 풀기
        actions = [
            (currAlp + 1, currCop, 1),
            (currAlp, currCop + 1, 1),
        ]
        
        for p in problems:
            if currAlp >= p[0] and currCop >= p[1]:
                actions.append((currAlp + p[2], currCop + p[3], p[4]))
        
        for nextAlp, nextCop, timeCost in actions:
            # 다음 능력치가 최종 목표치를 넘지 않도록 조정
            nextAlp = min(nextAlp, maxAlp)
            nextCop = min(nextCop, maxCop)
            
            # 최단 시간으로 갱신될 때만 큐에 추가
            if dp[nextAlp][nextCop] > dp[currAlp][currCop] + timeCost:
                dp[nextAlp][nextCop] = dp[currAlp][currCop] + timeCost
                q.append((nextAlp, nextCop))

    return dp[maxAlp][maxCop]