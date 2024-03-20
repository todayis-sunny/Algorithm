# 1865. [diff_4] 동철이의 일 분배.

def dfs(idx, value):
    global answer
    if value <= answer:
        return
    if idx == N:
        answer = value
    for i in range(N):
        if not visited[i]:
            visited[i] = True
            dfs(idx+1, value*arr[idx][i])
            visited[i] = False


for tc in range(1, int(input())+1):
    N = int(input())
    answer = 0
    visited = [False] * N

    arr = [[num / 100 for num in map(int, input().split())] for _ in range(N)]
    dfs(0, 1)
    answer *= 100
    print(f"#{tc} {round(answer, 6):.6f}")