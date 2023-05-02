# 1873. 상호의 배틀필드 <D3>

tc = int(input())

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
tank = ['^', 'v', '<', '>']
key = ['U', 'D', 'L', 'R']

for t in range(1, tc + 1):
    h, w = map(int, input().split())
    board = [list(map(str, input())) for _ in range(h)]
    for i in range(h):
        for j in range(w):
            # 탱크의 위치를 찾고 방향 인덱스를 찾는다
            if board[i][j] in tank:
                for tk in tank:
                    if board[i][j] == tk:
                        x, y = i, j
                        idx = tank.index(tk)

    n = int(input())
    order = str(input())
    for k in order:
        # 발사
        if k == 'S':
            a = 1
            # 발사할 목표 좌표
            nx = x + dx[idx] * a
            ny = y + dy[idx] * a
            while 0 <= nx < h and 0 <= ny < w:
                # 강철로 만들어진 벽이라면 무시하고 탈출
                if board[nx][ny] == '#':
                    break
                if board[nx][ny] == '*':
                    board[nx][ny] = '.'
                    break
                # 포탄은 계속 전진
                a += 1
                nx = x + dx[idx] * a
                ny = y + dy[idx] * a
        # 이동
        else:
            idx = key.index(k)
            # 탱크 방향 틀기
            board[x][y] = tank[idx]
            # 이동할 목표 좌표
            nx = x + dx[idx]
            ny = y + dy[idx]
            # 맵 범위에 들고 이동하는 곳이 평지라면 이동
            if 0 <= nx < h and 0 <= ny < w and board[nx][ny] == '.':
                # 탱크와 평지 스왑후 탱크 현재 좌표 갱신
                board[nx][ny], board[x][y] = board[x][y], board[nx][ny]
                x, y = nx, ny

    print(f'#{t}', end=' ')
    for b in board:
        print(*b, sep='')
