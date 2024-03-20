# 1865. [diff_4] 동철이의 일 분배.

def dfs(n, bits):
    if n == N:
        return 1
    if mem[bits] == 0:
        mx = 0
        for i in range(N):
            if (bits & (1 << i)) == 0:
                mx = max(mx, dfs(n+1, bits + (1 << i)) * arr[n][i])
        mem[bits] = mx
    return mem[bits]


for tc in range(1, int(input())+1):
    N = int(input())
    arr = [[num / 100 for num in map(int, input().split())] for _ in range(N)]
    mem = [0]*(2**N)

    answer = dfs(0, 0) * 100
    print(f"#{tc} {answer:.6f}")
