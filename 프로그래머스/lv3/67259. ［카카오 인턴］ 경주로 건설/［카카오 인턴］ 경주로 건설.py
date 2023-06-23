from collections import deque

INF = 1e9
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def bfs(x, y, idx, board, visited):
    dq = deque()
    dq.append((x, y, idx))
    while dq:
        x, y, idx = dq.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            # 직선도로 비용 1증가, 코너도로 비용 6증가
            cost = 1 if i == idx else 6
            # 출발점의 두 방향중 코너도로로 판별되면 무시
            if x == 0 and y == 0 and cost == 6:
                continue
            # 범위를 벗어난 경우 무시
            if nx < 0 or nx >= len(board) or ny < 0 or ny >= len(board):
                continue
            # 벽인 경우 무시
            if board[nx][ny] == 1:
                continue
            # 이미 최소 경로가 있는 경우 무시
            if visited[x][y][idx] + cost >= visited[nx][ny][i]:
                continue
            else:
                visited[nx][ny][i] = visited[x][y][idx] + cost
                dq.append((nx, ny, i))
                
def solution(board):
    visited = [[[INF, INF, INF, INF] for _ in range(len(board))] for _ in range(len(board))]
    visited[0][0] = [0, 0, 0, 0]
    # 하, 우 방향만 검사
    for idx in [1,3]:
        bfs(0, 0, idx, board, visited)
    answer = min(visited[-1][-1]) * 100

    return answer