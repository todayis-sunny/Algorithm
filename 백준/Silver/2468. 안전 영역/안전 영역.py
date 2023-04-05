# 2468. 안전 영역 [S1]
import sys
sys.setrecursionlimit(100000)

n = int(input())
graph = []
min_height = 100
max_height = 0
for _ in range(n):
    arr = list(map(int, sys.stdin.readline().split()))
    min_height = min(min_height, min(arr))
    max_height = max(max_height, max(arr))
    graph.append(arr)


def dfs(x, y, h):
    # 주어진 범위를 벗어나는 경우에는 즉시 종료
    if x < 0 or x >= n or y < 0 or y >= n:
        return False
    # 물에 잠기지 않은 지점
    if graph[x][y] > h and visited[x][y] == False:
        # 해당 지점 방문 처리
        visited[x][y] = True
        # 상, 하, 좌, 우 지점도 모두 재귀적으로 호출
        dfs(x - 1, y, h)
        dfs(x + 1, y, h)
        dfs(x, y - 1, h)
        dfs(x, y + 1, h)
        return True
    return False


answer = 1
for h in range(min_height, max_height + 1):
    result = 0
    visited = [[False] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            # 현재 위치에서 DFS 수행
            if dfs(i, j, h) == True:
                result += 1
    answer = max(answer, result)
print(answer)
