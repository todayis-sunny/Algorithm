# 9205. 맥주 마시면서 걸어가기 [G5]

import sys
from collections import deque

input = sys.stdin.readline


def bfs():
    queue = deque()
    queue.append((home[0], home[1]))
    while queue:
        x, y = queue.popleft()
        if abs(x - fest[0]) + abs(y - fest[1]) <= 1000:
            print('happy')
            return
        for i in range(n):
            if not visited[i]:
                nx, ny = conv[i]
                if abs(x - nx) + abs(y - ny) <= 1000:
                    queue.append((nx, ny))
                    visited[i] = True
    print('sad')
    return


t = int(input())
for i in range(t):
    n = int(input())
    home = [int(x) for x in input().split()]
    conv = []
    for j in range(n):
        x, y = map(int, input().split())
        conv.append((x, y))
    fest = [int(x) for x in input().split()]
    visited = [False for i in range(n + 1)]
    bfs()
