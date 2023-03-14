def solution(board):
    n = len(board)
    temp = [[0 for col in range(n+2)] for row in range(n+2)]
    for r in range(n):
        for c in range(n):
            if board[r][c] == 1:
                adj(temp, r+1, c+1)
            else:
                continue
    cnt = 0
    for r in range(1, n+1):
        for c in range(1, n+1):
            if temp[r][c] == 0:
                cnt += 1
    return cnt

def adj(a, r, c):
    a[r-1][c-1] += 0.01
    a[r-1][c] += 0.01
    a[r-1][c+1] += 0.01
    
    a[r][c-1] += 0.01
    a[r][c] += 0.01
    a[r][c+1] += 0.01
    
    a[r+1][c-1] += 0.01
    a[r+1][c] += 0.01
    a[r+1][c+1] += 0.01