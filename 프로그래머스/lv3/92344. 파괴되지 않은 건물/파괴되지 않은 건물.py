def solution(board, skill):
    count = 0
    imos = [[0] * (len(board[0]) + 1) for _ in range(len(board) + 1)]
    
    for S in skill:
        t, r1, c1, r2, c2, degree = S
        imos[r1][c1] += ((-1)**t) * degree
        imos[r1][c2 + 1] -= ((-1)**t) * degree
        imos[r2 + 1][c1] -= ((-1)**t) * degree
        imos[r2 + 1][c2 + 1] += ((-1)**t) * degree
    
    # 행 방향 누적합
    for row in range(len(imos)):
        for col in range(1, len(imos[0])):
            imos[row][col] += imos[row][col - 1]
    
    # 열 방향 누적합
    for col in range(len(imos[0])):
        for row in range(1, len(imos)):
            imos[row][col] += imos[row - 1][col]
            
    # 기존 보드와 더하기
    for row in range(len(board)):
        for col in range(len(board[0])):
            board[row][col] += imos[row][col]
            
            if board[row][col] > 0:
                count += 1
    
    return count