# 1697. 숨바꼭질 [S1]

from collections import deque

# 수빈 : n / 동생 : k
n, k = map(int, input().split())
max_num = 100000
visited = [0] * (max_num + 1)


def bfs():
    queue = deque()
    queue.append(n)

    while queue:
        x = queue.popleft()
        if x == k:
            print(visited[x])
            break
        for i in (x-1, x+1, x*2):
            if 0 <= i <= max_num and visited[i] == 0:
                visited[i] = visited[x] + 1
                queue.append(i)


bfs()