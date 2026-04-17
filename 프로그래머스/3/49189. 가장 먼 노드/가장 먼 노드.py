from collections import deque

def solution(n, edge):
    visited = [False] * (n + 1)
    adj = [[] for _ in range(n + 1)]
    for e in edge:
        adj[e[0]].append(e[1])
        adj[e[1]].append(e[0])
    return bfs(adj, visited)



def bfs(adj, visited):
    global ans
    maxDist = -1
    count = 0
    q = deque([(1, 0)])
    visited[1] = True
    while q:
        curr, dist = q.popleft()
        for node in adj[curr]:
            if visited[node]: # 방문했으면 스킵
                continue
            if dist > maxDist: # 최대 방문치보다 크면 갱신
                maxDist = dist
                count = 0
            count += 1
            q.append((node, dist + 1))
            visited[node] = True
    return count
