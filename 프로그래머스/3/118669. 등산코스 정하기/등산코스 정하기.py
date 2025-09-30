import heapq

INF = 1e9
def solution(n, paths, gates, summits):
    # 양방향 인접행렬
    adj = [[] for _ in range(n + 1)] # 1-based
    # 등산시간 set
    setIntensity = set()
    # 등산로 초기화
    for i, j, w in paths:
        adj[i].append((j, w))
        adj[j].append((i, w))
        setIntensity.add(w)
    # 산봉우리 판별
    isSummit = [False] * (n + 1)
    for s in summits:
        isSummit[s] = True
    # 우선순위 큐 선언
    hq = []
    # 거리 초기화
    distance = [INF] * (n + 1)
    # 출발지 다 담아주기
    for g in gates:
        distance[g] = 0
        heapq.heappush(hq, (0, g))
    # 다익스트라
    while hq:
        currDist, currNode = heapq.heappop(hq)
        # 산봉우리면 스킵
        if isSummit[currNode]:
            continue
        # 최소 거리가 있으면 스킵
        if distance[currNode] < currDist:
            continue
        for nextNode, nextDist in adj[currNode]:
            nextDist = max(distance[currNode], nextDist)
            if distance[nextNode] > nextDist:
                distance[nextNode] = nextDist
                heapq.heappush(hq, (nextDist, nextNode))
    
    # 산봉우리 번호, intensity의 최솟값
    ans = [INF, INF]
    # 반환
    for s in sorted(summits):
        if distance[s] < ans[1]:
            ans[0] = s
            ans[1] = distance[s]
    
    return ans