# 2819. 격자판의 숫자 이어 붙이기 <D4>


def dfs(x, y, s):
    if len(s) == 7:
        result.add(s)
        return
    
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        
        if 0 <= nx < 4 and 0 <= ny < 4:
            dfs(nx, ny, s+arr[nx][ny])


dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

tc = int(input())

for t in range(1, tc + 1):
    arr = [list(map(str, input().split())) for _ in range(4)]
    result = set()
    for i in range(4):
        for j in range(4):
            dfs(i, j, '')

    print(f'#{t} {len(result)}')
