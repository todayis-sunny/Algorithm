def solution(board):
    for i in range(3):
        board[i] = list(board[i])
    
    # O, X 갯수 판별
    cntO = 0
    cntX = 0
    for i in range(3):
        cntO += board[i].count('O')
        cntX += board[i].count('X')
    if not 0<= cntO - cntX <= 1:
        return 0
    
    # 중복 승리 판별
    winO = 0
    winX = 0
    # 가로
    for r in range(3):
        tmp = ''
        for c in range(3):
            tmp += board[r][c]
        if tmp == 'OOO':
            winO += 1
        elif tmp == 'XXX':
            winX += 1
    # 세로
    for c in range(3):
        tmp = ''
        for r in range(3):
            tmp += board[r][c]
        if tmp == 'OOO':
            winO += 1
        elif tmp == 'XXX':
            winX += 1
    # 대각
    diag1 = board[0][0] + board[1][1] + board[2][2]
    diag2 = board[0][2] + board[1][1] + board[2][0]
    if diag1 == 'OOO':
        winO += 1
    elif diag1 == 'XXX':
        winX += 1
    if diag2 == 'OOO':
        winO += 1
    elif diag2 == 'XXX':
        winX += 1
        
    if winO > 0 and winX > 0: # 둘 다 이긴 경우
        return 0
    elif winO > 0 and winX == 0 and cntO == cntX: # O가 이겼는데 O,X의 갯수가 같은 경우
        return 0
    elif winO == 0 and winX > 0 and cntO != cntX: # X가 이겼는데 O,X의 갯수가 같지 않은 경우
        return 0
    else: # 위의 상황을 제외하면 정상
        return 1