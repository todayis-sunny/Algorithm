# 2819. 격자판의 숫자 이어 붙이기 <D4>


def dfs(x, y, s):
    if x < 0 or x >= 4 or y < 0 or y >= 4:
        return
    s += arr[x][y]
    if len(s) < 7:
        dfs(x-1, y, s)
        dfs(x+1, y, s)
        dfs(x, y-1, s)
        dfs(x, y+1, s)
    else:
        if s not in ans:
            ans.append(s)


tc = int(input())

for t in range(1, tc + 1):
    arr = [list(map(str, input().split())) for _ in range(4)]
    ans = []
    for i in range(4):
        for j in range(4):
            dfs(i, j, '')

    print(f'#{t} {len(ans)}')
