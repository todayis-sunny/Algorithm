from collections import deque

INF = float('inf')

def solution(alp, cop, problems):
    # 1. 최종 목표 능력치 설정
    max_alp = max([p[0] for p in problems])
    max_cop = max([p[1] for p in problems])
    
    # 시작 알고력과 코딩력이 목표치보다 높을 수 있으므로 최대치를 갱신
    if alp >= max_alp and cop >= max_cop:
        return 0 # 이미 모든 문제를 풀 수 있으면 시간 0
        
    if alp > max_alp: alp = max_alp
    if cop > max_cop: cop = max_cop
    
    # 2. DP 테이블 초기화
    dp = [[INF] * (max_cop + 2) for _ in range(max_alp + 2)]
    dp[alp][cop] = 0

    # 3. BFS 탐색 시작
    q = deque([(alp, cop)])
    
    while q:
        curr_alp, curr_cop = q.popleft()
        
        # 3가지 행동 (알고력+1, 코딩력+1, 문제 풀기) 탐색
        for next_alp, next_cop, time_cost in [
            (curr_alp + 1, curr_cop, 1), # 알고력 공부
            (curr_alp, curr_cop + 1, 1), # 코딩력 공부
        ] + [(curr_alp + p[2], curr_cop + p[3], p[4]) for p in problems if curr_alp >= p[0] and curr_cop >= p[1]]: # 문제 풀이
            
            # 다음 능력치가 최종 목표치를 넘지 않도록 조정
            next_alp = min(next_alp, max_alp)
            next_cop = min(next_cop, max_cop)
            
            # 최단 시간으로 갱신될 때만 큐에 추가
            if dp[next_alp][next_cop] > dp[curr_alp][curr_cop] + time_cost:
                dp[next_alp][next_cop] = dp[curr_alp][curr_cop] + time_cost
                q.append((next_alp, next_cop))

    return dp[max_alp][max_cop]