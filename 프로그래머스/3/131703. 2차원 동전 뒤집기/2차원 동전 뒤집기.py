INF = 1e9

def solution(beginning, target):
    rowMax = len(beginning)
    colMax = len(beginning[0])
    goal = [[0] * colMax for _ in range(rowMax)]
    for r in range(rowMax):
        for c in range(colMax):
            # XOR 연산으로 다른 구역만 1로 체크
            goal[r][c] = beginning[r][c] ^ target[r][c]
    
    # 0행을 뒤집지 않고 진행
    answer = INF
    count = 0
    board = [[0] * colMax for _ in range(rowMax)]
    for c in range(colMax):
        # c열을 뒤집지 않아도 된다면 스킵
        if goal[0][c] == 0:
            continue
        count += 1
        for r in range(rowMax):
            board[r][c] += 1
    # 나머지 행 검사
    for r in range(1, rowMax):
        # r행을 뒤집지 않아도 된다면 스킵
        if goal[r][0] == board[r][0]:
            continue
        count += 1
        for c in range(colMax):
            board[r][c] += 1
    if check(board, goal):
        answer = count
    
    # 0행을 뒤집고 진행
    count = 1
    board = [[0] * colMax for _ in range(rowMax)]
    for c in range(colMax):
        board[0][c] += 1
    
    for c in range(colMax):
        # c열을 뒤집지 않아도 된다면 스킵
        if goal[0][c] == 1:
            continue
        count += 1
        for r in range(rowMax):
            board[r][c] += 1
    # 나머지 행 검사
    for r in range(1, rowMax):
        # r행을 뒤집지 않아도 된다면 스킵
        if goal[r][0] == board[r][0]:
            continue
        count += 1
        for c in range(colMax):
            board[r][c] += 1
    if check(board, goal):
        answer = min(count, answer)
        
    if answer == INF:
        return -1
    return answer

def check(board, goal):
    for r in range(len(board)):
        for c in range(len(board[0])):
            if board[r][c] % 2 != goal[r][c]:
                return False
    return True