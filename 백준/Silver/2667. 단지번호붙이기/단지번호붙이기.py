# 2667. 단지번호붙이기 [S1]

# N을 입력받기
n = int(input())

# 2차원 리스트의 맵 정보 입력받기
graph = []
for i in range(n):
    graph.append(list(map(int, input())))

# DFS로 특젖한 노드를 방문한 뒤에 연결된 모든 노드들도 방문
def dfs(x, y):
    # 주어진 범위를 벗어나는 경우에는 즉시 종료
    global cnt
    if x < 0 or x >= n or y < 0 or y >= n:
        return False
    # 현재 노드를 아직 방문하지 않았다면
    if graph[x][y] == 1:
        # 해당 노드 방문 처리
        graph[x][y] = 0
        cnt += 1
        # 상, 하, 좌, 우의 위치도 모두 재귀적으로 호출
        dfs(x - 1, y)
        dfs(x + 1, y)
        dfs(x, y - 1)
        dfs(x, y + 1)
        return True
    return False

# 모든 노드(위치)에 대하여 단지수 구하기
global cnt
result = 0
answer = []
for i in range(n):
    for j in range(n):
        cnt = 0
        # 현재 위치에서 DFS 수행
        if dfs(i, j):
            answer.append(cnt)
            result += 1

print(result)
for element in sorted(answer):
    print(element)