# 1486. 장훈이의 높은 선반 <D4>


def dfs(idx, total):
    global ans
    # 마지막 사람까지 온 경우
    if idx == n:
        if h <= total <= ans:
            ans = total
        return
    # 가지치기
    if total >= ans:
        return

    dfs(idx + 1, total)
    dfs(idx + 1, total + height[idx])


for t in range(1, int(input()) + 1):
    n, h = map(int, input().split())
    height = list(map(int, input().split()))
    ans = 1e9

    dfs(0, 0)
    print(f'#{t} {ans - h}')
