# 2817. 부분 수열의 합 <D3>

def dfs(idx, tmp):
    global ans
    if k < tmp:
        return

    if idx == n:
        if k == tmp:
            ans += 1
        return

    # 본인을 더할 시
    dfs(idx + 1, tmp)
    # 본인을 더하지 않을 시
    dfs(idx + 1, tmp + arr[idx])


tc = int(input())

for t in range(1, tc + 1):
    n, k = map(int, input().split())
    arr = list(map(int, input().split()))
    ans = 0
    dfs(0, 0)
    print(f'#{t} {ans}')