from itertools import product

types = ["", "A", "B", "C"]
def solution(n, infection, edges, k):
    # 루트 구하기
    def dfs(cur, string):
        for nxt in adj[cur]:
            # 이미 감염된 경우
            if visited[nxt[0]]:
                continue
            # 새롭게 감염되는 경우
            route[nxt[0]] = string + nxt[1]
            visited[nxt[0]] = True
            dfs(nxt[0], string + nxt[1])
    
    
    # 각 지점까지 도달하는 루트
    route = [""] * (n + 1)
    # 간선 정보 확인하기
    adj = [[] for _ in range(n + 1)] 
    for a, b, t in edges:
        adj[a].append((b, types[t]))
        adj[b].append((a, types[t]))   
    # 감염정보
    visited = [False] * (n + 1)
    visited[infection] = True
    # 감염 루트 구하기
    dfs(infection, "")
    # 가능한 중복순열 계산하기
    result = 0
    for p in product(types[1:], repeat = k):
        case = "".join(p)
        count = 0
        # print("## :", case)
        # 하나씩 감염이 가능한지 확인한기
        for i in range(1, n + 1):
            # print(route[i])
            limit = len(route[i])
            idx = 0 # 감염 루트의 인덱스
            caseIdx = 0 # 제공된 케이스의 인덱스
            # 감염루트가 제공된 케이스에 포함되는지 확인하기
            while idx < limit and caseIdx < k:
                # 감염루트가 현재 열린 파이프 종류와 동일한 경우
                if route[i][idx] == case[caseIdx]:
                    idx += 1
                # 동일하지 않은 경우
                else:
                    caseIdx += 1
            # 감염이 완료된 경우
            if idx == limit:
                count += 1
        result = max(result, count)
                
    return result