# 12712. 파리퇴치3 <D2>

for t in range(1, int(input()) + 1):
    N, M = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(N)]

    answer = 0
    for x in range(N):
        for y in range(N):
            a_cnt = arr[x][y]
            b_cnt = arr[x][y]
            for k in range(1, M):
                for ax, ay in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                    if 0 <= x + k*ax < N and 0 <= y + k*ay < N:
                        a_cnt += arr[x + k*ax][y + k*ay]
                    else:
                        continue

                for bx, by in [(-1, -1), (-1, 1), (1, -1), (1, 1)]:
                    if 0 <= x + k*bx < N and 0 <= y + k*by < N:
                        b_cnt += arr[x + k*bx][y + k*by]
                    else:
                        continue

            answer = max(answer, a_cnt, b_cnt)
    print(f"#{t} {answer}")
