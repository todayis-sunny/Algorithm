# 11315. 오목 판정 <D3>

tc = int(input())

def omok():
    for r in range(n):
        for c in range(n):
            for dr, dc in ((0, 1), (1, 0), (1, 1), (1, -1)):
                for mul in range(5):
                    nr, nc = r + dr * mul, c + dc * mul
                    if 0 <= nr < n and 0 <= nc < n and board[nr][nc] == 'o':
                        pass
                    else:
                        break
                # break없이 반복문이 완전히 다 돌고 나오면
                else:
                    return 'YES'
    return 'NO'


for t in range(1, tc + 1):
    n = int(input())
    board = [list(map(str, input())) for _ in range(n)]

    ans = omok()

    print(f'#{t} {ans}')