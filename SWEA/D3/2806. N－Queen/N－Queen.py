# 2806. N-Queen <D3>

def check(n):
    for i in range(n):
        if (board[n] == board[i]) or (n-i == abs(board[n]-board[i])):
            return False

    return True


def dfs(n):
    global ans

    if n == N:
        ans += 1
        return

    for i in range(N):
        if visited[i]:
            continue
        board[n] = i
        if check(n):
            visited[i] = True
            dfs(n+1)
            visited[i] = False


for t in range(1, int(input()) + 1):
    N = int(input())
    board = [0 for _ in range(N)]
    visited = [False for _ in range(N)]
    ans = 0
    dfs(0)
    print(f'#{t} {ans}')