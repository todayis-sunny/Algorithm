dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def solution(board, h, w):
    def calculate(x, y):
        color = board[x][y]
        count = 0
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or nx >= len(board) or ny < 0 or ny >= len(board[0]):
                continue
            if board[nx][ny] != color:
                continue
            count += 1
        return count    
    
    return calculate(h, w)
