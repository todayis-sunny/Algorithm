def solution(board):
    N = len(board)
    M = len(board[0])

    # dp 준비
    dp = [[0] * M for _ in range(N)]
    dp[0] = board[0]
    for i in range(1, N):
        dp[i][0] = board[i][0]
    
    # 2중 포문으로 연산
    for i in range(1, N):
        for j in range(1, M):
            if board[i][j] == 1:
                dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1
    
    # 최대 넓이 구하기
    result = 0
    for i in range(N):
        tmp = max(dp[i])
        result = max(result, tmp)
    
    return result ** 2