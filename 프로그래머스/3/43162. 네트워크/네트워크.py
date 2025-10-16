def solution(n, computers):
    ans = 0
    
    visited = [False] * n
    for com in range(n):
        if visited[com] == False:
            dfs(n, computers, com, visited)
            ans += 1
    return ans

def dfs(n, computers, com, visited):
    visited[com] = True
    for connect in range(n):
        if connect != com and computers[com][connect] == 1:
            if visited[connect] == False:
                dfs(n, computers, connect, visited)