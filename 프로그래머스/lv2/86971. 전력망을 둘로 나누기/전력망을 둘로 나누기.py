from collections import deque

def bfs(graph, visited, n):
    cnt = 1
    dq = deque([])
    visited[1] = True
    for i in range(n+1):
        if graph[1][i]:
            dq.append(i)
    while dq:
        k = dq.popleft()
        cnt += 1
        visited[k] = True
        for i in range(n+1):
            if graph[k][i] and not visited[i]:
                dq.append(i)
    return cnt

def solution(n, wires):
    graph = [[False]*(n+1) for _ in range(n+1)]
    answer = 1e9
    
    for w1, w2 in wires:
        graph[w1][w2] = True
        graph[w2][w1] = True
    
    for w1, w2 in wires:
        visited = [False] * (n+1)
        graph[w1][w2] = False
        graph[w2][w1] = False
        cnt = bfs(graph, visited, n)
        graph[w1][w2] = True
        graph[w2][w1] = True
        answer = min(answer, abs(cnt - (n-cnt)))
    return answer