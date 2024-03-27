# 02115. 벌꿀채취.

def dfs(idx, c, total, x, y):
    if idx == M:
        honey[x][y] = max(honey[x][y], total)
        return
    if beehive[x][y + idx] <= c:
        dfs(idx + 1, c - beehive[x][y + idx], total + beehive[x][y + idx] ** 2, x, y)  # 수학하는 경우.
    dfs(idx + 1, c, total, x, y)  # 수학하지 않는 경우.


for tc in range(1, int(input()) + 1):
    N, M, C = map(int, input().split())
    beehive = [list(map(int, input().split())) for _ in range(N)]
    honey = [[0] * N for _ in range(N)]

    limit = N - M
    pointer = 0
    # 벌꿀의 최대값 저장.
    while pointer < N * N:
        div, mod = divmod(pointer, N)
        if mod > limit:
            pointer = (div + 1) * N
            continue
        dfs(0, C, 0, div, mod)
        pointer += 1

    answer = 0
    for h1 in range(N * N):
        x1, y1 = divmod(h1, N)
        if honey[x1][y1] == 0:
            continue
        for h2 in range(h1 + M, N * N):
            x2, y2 = divmod(h2, N)
            if honey[x1][y1] == 0:
                continue
            answer = max(answer, honey[x1][y1] + honey[x2][y2])

    print(f"#{tc} {answer}")
