# 1251. [diff_4] 하나로.
import heapq


def find(x):
    if x != parent[x]:
        parent[x] = find(parent[x])
    return parent[x]


def union(a, b):
    a = find(a)
    b = find(b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b


def getCost(x1, y1, x2, y2):
    return abs(x2-x1) ** 2 + abs(y2-y1) ** 2


for tc in range(1, int(input()) + 1):
    N = int(input())
    arr = []
    parent = []
    X = list(map(int, input().split()))
    Y = list(map(int, input().split()))
    for i in range(N):
        parent.append(i)
    E = float(input())
    q = []
    answer = 0
    for i in range(0, N):
        for j in range(i + 1, N):
            cost = getCost(X[i], Y[i], X[j], Y[j])
            heapq.heappush(q, (cost, i, j))

    for _ in range(len(q)):
        data = heapq.heappop(q)
        cost = data[0]
        a = data[1]
        b = data[2]
        if find(a) != find(b):
            union(a, b)
            answer += cost
    answer *= E

    print(f"#{tc} {round(answer)}")
