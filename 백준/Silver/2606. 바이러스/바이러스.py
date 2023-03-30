# 2606. 바이러스 [S3]

from collections import deque

computers = int(input())
N = int(input())
matrix = [[0] * (computers + 1) for i in range(computers + 1)]

visited = [0] * (computers + 1)
for i in range(N):
    a, b = map(int, input().split())
    matrix[a][b] = matrix[b][a] = 1


def bfs(c):
    queue = deque([c])
    visited[c] = 1

    while queue:
        c = queue.popleft()
        for i in range(1, computers + 1):
            if visited[i] == 0 and matrix[c][i] == 1:
                queue.append(i)
                visited[i] = 1


bfs(1)
print(visited.count(1) - 1)