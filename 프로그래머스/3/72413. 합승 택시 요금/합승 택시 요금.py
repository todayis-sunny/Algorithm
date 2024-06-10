import heapq

def solution(n, s, a, b, fares):
    INF = 1e9
    
    # 인접리스트로 변경.
    maps = [[] for _ in range(n+1)]
    for v, u, c in fares:
        maps[v].append((u, c))
        maps[u].append((v, c))
    
    # 다익스트라.
    def dijkstra(start):
        graph = [INF] * (n+1)
        graph[start] = 0
        queue = [(0, start)]
        
        while queue:
            currDist, currNode = heapq.heappop(queue)
            
            if graph[currNode] < currDist:
                continue
                
            for nextNode, nextDist in maps[currNode]:
                if graph[nextNode] > currDist + nextDist:
                    graph[nextNode] = currDist + nextDist
                    heapq.heappush(queue, (currDist + nextDist, nextNode))
        return graph
    
    # i번째(특정 노드)에서 시작해서 모든 정점으로 도착하는 최단거리를 미리 구함.
    D = [0] + [dijkstra(i) for i in range(1, n+1)]
    
    path = INF
    for i in range(1, n+1):
        path = min(path, D[s][i] + D[i][a] + D[i][b])
        
    return path
        
    
    return answer