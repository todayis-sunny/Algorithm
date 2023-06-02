def solution(board):
    for i in range(3):
        board[i] = list(board[i])
    
    # O, X 갯수 판별
    cnt_O = 0
    cnt_X = 0
    for i in range(3):
        cnt_O += board[i].count('O')
        cnt_X += board[i].count('X')
    if not 0<= cnt_O - cnt_X <= 1:
        return 0
    
    # 중복 승리 판별
    win_O = 0
    win_X = 0
    # 가로
    for r in range(3):
        tmp = ''
        for c in range(3):
            tmp += board[r][c]
        if tmp == 'OOO':
            win_O += 1
        elif tmp == 'XXX':
            win_X += 1
    # 세로
    for c in range(3):
        tmp = ''
        for r in range(3):
            tmp += board[r][c]
        if tmp == 'OOO':
            win_O += 1
        elif tmp == 'XXX':
            win_X += 1
    # 대각
    diag1 = board[0][0] + board[1][1] + board[2][2]
    diag2 = board[0][2] + board[1][1] + board[2][0]
    if diag1 == 'OOO':
        win_O += 1
    elif diag1 == 'XXX':
        win_X += 1
    if diag2 == 'OOO':
        win_O += 1
    elif diag2 == 'XXX':
        win_X += 1
        
    if win_O > 0 and win_X > 0: # 둘 다 이긴 경우
        return 0
    elif win_O > 0 and win_X == 0 and cnt_O == cnt_X: # O가 이겼는데 O,X의 갯수가 같은 경우
        return 0
    elif win_O == 0 and win_X > 0 and cnt_O != cnt_X: # X가 이겼는데 O,X의 갯수가 같지 않은 경우
        return 0
    else: # 위의 상황을 제외하면 정상
        return 1