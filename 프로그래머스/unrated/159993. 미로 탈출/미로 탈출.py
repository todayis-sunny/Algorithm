from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def solution(maps):

    def bfs(x, y):
        dq = deque()
        dq.append((x, y))
        visited[x][y] = 0
        while dq:
            x, y = dq.popleft()  
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                if 0 <= nx < len(maps) and 0 <= ny < len(maps[i]):
                    if maps[nx][ny] != 'X' and visited[nx][ny] == -1:
                        dq.append((nx, ny))
                        visited[nx][ny] = visited[x][y] + 1
                    
    # solution 시작
    answer = 0
    for i in range(len(maps)):
        maps[i] = list(maps[i])
        if 'S' in maps[i]:
            S = (i, maps[i].index('S'))
        if 'L' in maps[i]:
            L = (i, maps[i].index('L'))
        if 'E' in maps[i]:
            E = (i, maps[i].index('E'))
    visited = [[-1] * len(maps[i]) for _ in range(len(maps))]
    # 레버까지 가기
    bfs(S[0], S[1])
    # 레버 미도달 시 종료
    if visited[L[0]][L[1]] == -1:
        return -1
    else:
        answer += visited[L[0]][L[1]]
        visited = [[-1] * len(maps[i]) for _ in range(len(maps))]
        bfs(L[0], L[1])
    # 종료 지점 미도달 시 종료
    if visited[E[0]][E[1]] == -1:
        return -1
    else:
        answer += visited[E[0]][E[1]]
        return answer