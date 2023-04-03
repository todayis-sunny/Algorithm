# 5014. 스타트링크 [S1]

from collections import deque

# 입력부 - f : 총 층 | s : 강호가 있는 층 | g : 스타트링크가 있는 층 | u : 위 | d : 아래
f, s, g, u, d = map(int, input().split())
visited = [0] * (f + 1)


def bfs():
    queue = deque()
    queue.append(s)

    while queue:
        y = queue.popleft()
        if y == g:
            print(visited[y])
            return
        for i in (y+u, y-d):
            if i != y and 1 <= i <= f and visited[i] == 0:
                visited[i] = visited[y] + 1
                queue.append(i)
    print('use the stairs')


bfs()