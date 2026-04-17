from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

INF = 1e9
def solution(h, grid, panels, seqs):
    # 지점 p에서 각 정점들까지의 거리
    def bfs(p):
        dq = deque()
        px, py = panels[p][1], panels[p][2]
        dq.append((px, py))
        # 거리를 0으로 시작하지 않고 1로 시작 추후에 1을 빼서 계산(시작점이 2로 초기화 되는걸 방지)
        minimap[p][px][py] = 1
        while dq:
            x, y = dq.popleft()
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                # 범위 초과인 경우
                if nx < 0 or nx >= len(grid) or ny < 0 or ny >= len(grid[0]):
                    continue
                # 벽인 경우
                if grid[nx][ny] == '#':
                    continue
                # 이미 방문한 경우
                if minimap[p][nx][ny] != 0:
                    continue
                minimap[p][nx][ny] = minimap[p][x][y] + 1
                dq.append((nx, ny))
        return
    
    # 실질적인 소요시간 계산
    def getRequiredTime(p1, p2):
        # 층이 다른 경우
        if panels[p1][0] != panels[p2][0]:
            ex, ey = panels[0][1], panels[0][2]
            # 소요시간 = 층간의 차이 + 각 위치에서 엘리베이터까지의 거리(p1, p2)
            distance[p1][p2] = distance[p2][p1] = abs(panels[p2][0] - panels[p1][0]) + (minimap[p1][ex][ey] - 1) + (minimap[p2][ex][ey] - 1)
        # 층이 같은 경우
        else:
            px, py = panels[p2][1], panels[p2][2]
            distance[p1][p2] = distance[p2][p1] = minimap[p1][px][py] - 1
            return
    
    
    # dfs
    def tsp(prv, bit):
        # 모든 지점을 다 방문한 경우 (0번 패널은 엘리베이터이므로 -2 처리 ex. 111110 )
        if bit == ((1 << len(panels)) - 2):
            return 0
        # 이미 방문한 지점인지 확인
        if dp[prv][bit] != INF:
            return dp[prv][bit]
        # 방문하지 않은 지점 탐색
        for nxt in range(1, len(panels)):
            # 이미 방문한 경우 스킵
            if (bit & (1 << nxt)) != 0:
                continue
            # 방문할 수 없는 경우 (우선순위를 만족하지 못하는 경우) 스킵
            if (bit & priority[nxt]) != priority[nxt]:
                continue
            # 새롭게 방문할 수 있는 경우
            nxtBit = (bit | (1 << nxt))
            dp[prv][bit] = min(dp[prv][bit], tsp(nxt, nxtBit) + distance[prv][nxt])
        return dp[prv][bit]
    
    ### ---- solution 코드 ----
    # 엘리베이터 좌표
    elev = [0, -1, -1]
    panels = [elev] + panels
    # [i][j] = k : i번 패널에서 j번 패널까지의 거리 k
    distance = [[0 for _ in range(len(panels))] for _ in range(len(panels))]
    # [p][r][c] = 패널 p로 부터 (r, c)의 좌표 거리
    minimap = [[[0 for _ in range(len(grid[0]))] for _ in range(len(grid))] for _ in range(len(panels))]
    # 우선순위 비트로 표현
    priority = [0] * len(panels)
    # dp[prv][bit] = k : bit상태로 방문을 했고 이전 prv지점에 있었을때 나머지 패널들을 킬때의 최소비용
    dp = [[INF] * (1 << len(panels)) for _ in range(len(panels))]
    # 0. 엘리베이터 위치 계산
    for r in range(len(grid)):
        for c in range(len(grid[0])):
            # 엘리베이터인 경우
            if grid[r][c] == '@':
                panels[0][1] = r
                panels[0][2] = c
    # 1. 각 패널위치 1-based -> 0-based로 변경
    for p in range(1, len(panels)):
        panels[p][1] -= 1
        panels[p][2] -= 1
    # 2. bfs로 p지점에서 각 지점까지 거리 구하기
    for p in range(len(panels)):
        bfs(p)

    # 3. p1에서 p2까지의 실질적인 시간(거리) 구하기
    for p1 in range(len(panels) - 1):
        for p2 in range(p1 + 1, len(panels)):
            getRequiredTime(p1, p2)
    
    # 4. 우선순위 부여하기
    for parent, child in seqs:
        priority[child] |= (1 << parent)
    
    # 5. 메인 로직(tsp) 실행 : 1번 패널시작(prv = 1) 모든 패널을 켜지 않은 상태(bit = 0)
    tsp(1, 0)
    return dp[1][0]





    
    
    

# 엘리베이터로 부터 거리를 찾는다.
# 같은 층에 있는 패널끼리의 거리를 찾는다.
# 엘리베이터를 0번 패널로 생각한다.

# 1번 패널로 부터 출발한다.
