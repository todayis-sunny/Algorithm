# 2644. 촌수계산 [S2]

# 입력 first : 전체 사람 n / second : 촌수를 계산해야 하는 두 사람 / third : 관계의 개수 m
n = int(input())
a, b = map(int, input().split())
m = int(input())

graph = [[] for _ in range(n+1)]
visited = [False] * (n+1)
result = []

for _ in range(m):
    x, y = map(int, input().split())
    graph[x].append(y)
    graph[y].append(x)


def dfs(v, num):
    num += 1
    visited[v] = True

    if v == b:
        result.append(num)

    for i in graph[v]:
        if not visited[i]:
            dfs(i, num)


dfs(a, 0)
if len(result) == 0:
    print(-1)
else:
    print(result[0]-1)