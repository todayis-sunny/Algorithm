# 3234. 준환이의 양팔저울 <D4>

# 좌측에 두는 행위 : left | 우측에 두는 행위 : right
def dfs(left, right, cnt):
    global case
    # 마지막 순서 종료
    if cnt == n:
        case += 1
        return
    # 추를 좌우측에 상관없이 놓아도 괜찮은 경우 (남은 추 : N) -> 2**N * N!
    if left >= limit - left:
        case += (2 ** (n - cnt)) * factorial[n - cnt]
        return

    for idx in range(n):
        if visited[idx]:
            continue
        # 앞선 행위가 가능하면 좌측에 두는 행위는 무조건 가능
        visited[idx] = True
        dfs(left + weight[idx], right, cnt + 1)
        if left >= right + weight[idx]:
            dfs(left, right + weight[idx], cnt + 1)
        visited[idx] = False

factorial = [0] * 10
factorial[1] = 1
for i in range(2, 10):
    factorial[i] = factorial[i-1] * i
for t in range(1, int(input()) + 1):
    n = int(input())
    weight = list(map(int, input().split()))
    limit = sum(weight)
    case = 0
    visited = [False] * n
    dfs(0, 0, 0)

    print(f'#{t} {case}')
