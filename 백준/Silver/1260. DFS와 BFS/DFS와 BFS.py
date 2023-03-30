# 1260. DFS와 BFS [S2]

from collections import deque

N, M, V = list(map(int, input().split()))

matrix = [[0]*(N+1) for i in range(N+1)]

visited_dfs = [0]*(N+1)
visited_bfs = [0]*(N+1)

for i in range(M):
    a,b = map(int, input().split())
    matrix[a][b] = matrix[b][a] = 1


def dfs(V):
    visited_dfs[V] = 1
    print(V, end=' ')

    for i in range(1, N+1):
        if visited_dfs[i] == 0 and matrix[V][i] == 1:
            dfs(i)


def bfs(V):
    queue = deque([V])
    visited_bfs[V] = 1

    while queue:
        V = queue.popleft()
        print(V, end=' ')
        for i in range(1, N+1):
            if visited_bfs[i] == 0 and matrix[V][i] == 1:
                queue.append(i)
                visited_bfs[i] = 1


dfs(V)
print()
bfs(V)