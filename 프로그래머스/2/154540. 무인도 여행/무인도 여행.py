from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(maps, x, y):
    cnt = 0
    dq = deque()
    dq.append((x, y))
    cnt += int(maps[x][y])
    maps[x][y] = 'X'
    
    while dq:
        x, y = dq.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < len(maps) and 0 <= ny < len(maps[0]) and maps[nx][ny] != 'X':
                dq.append((nx, ny))
                cnt += int(maps[nx][ny])
                maps[nx][ny] = 'X'
                
    return cnt
        
    
def solution(maps):
    for i in range(len(maps)):
        maps[i] = list(maps[i])
    answer = []
    for x in range(len(maps)):
        for y in range(len(maps[0])):
            if maps[x][y] != 'X':
                answer.append(bfs(maps, x, y))
    answer.sort()
    
    return answer if answer else [-1]