from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
                                
def solution(board):
    def bfs(x, y, t):
        dq = deque()
        dq.append((x, y, t))
        visited[x][y] = True
        while dq:
            x, y, t = dq.popleft()
            if board[x][y] == 'G':
                return t
            for i in range(4):
                for k in range(1, 101):
                    nx = x + dx[i]*k
                    ny = y + dy[i]*k
                    # 영역끝에 닿은 경우
                    if nx == -1 or nx == len(board) or ny == -1 or ny == len(board[i]):
                        nx = x + dx[i]*(k-1)
                        ny = y + dy[i]*(k-1)
                        break
                    # 장애물에 닿은 경우
                    if board[nx][ny] == 'D':
                        nx = x + dx[i]*(k-1)
                        ny = y + dy[i]*(k-1)
                        break
                if not visited[nx][ny]:
                    visited[nx][ny] = True
                    dq.append((nx, ny, t+1))
        return -1
                    
    # solution 시작
    for i in range(len(board)):
        board[i] = list(board[i])
        if 'R' in board[i]:
            rx, ry = i, board[i].index('R')

    visited = [[False] * len(board[i]) for _ in range(len(board))]
    
    return bfs(rx, ry, 0)