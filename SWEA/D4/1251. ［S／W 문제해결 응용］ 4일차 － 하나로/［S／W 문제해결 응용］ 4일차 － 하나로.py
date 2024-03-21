# 1251. [diff_4] 하나로.
import heapq


def getCost(x1, y1, x2, y2):
    return abs(x2-x1) ** 2 + abs(y2-y1) ** 2


for tc in range(1, int(input()) + 1):
    N = int(input())
    arr = []
    visited = [False] * N
    X = list(map(int, input().split()))
    Y = list(map(int, input().split()))
    E = float(input())
    q = []
    answer = 0
    heapq.heappush(q, (0, 0))
    count = N
    while count > 0:
        node = heapq.heappop(q)
        if visited[node[1]]:
            continue
        visited[node[1]] = True
        answer += node[0]
        count -= 1
        for i in range(N):
            if visited[i]:
                continue
            cost = getCost(X[node[1]], Y[node[1]], X[i], Y[i])
            heapq.heappush(q, (cost, i))

    answer *= E

    print(f"#{tc} {round(answer)}")
