# 7465. 창용 마을 무리의 개수 <D4>

from collections import deque

for t in range(1, int(input()) + 1):
    N, M = map(int, input().split())
    arr = [[] for _ in range(N+1)]
    for m in range(M):
        a, b = map(int, input().split())
        arr[a].append(b)
        arr[b].append(a)

    memo = [False] * (N + 1)
    memo[0] = True

    answer = 0
    dq = deque([])
    for i in range(N+1):
        if memo[i]:
            continue
        else:
            dq.append(i)
            memo[i] = True
            answer += 1
            while dq:
                number = dq.popleft()
                for num in arr[number]:
                    if not memo[num]:
                        dq.append(num)
                        memo[num] = True
    print(f"#{t} {answer}")
