from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]        
    

def solution(land):
    limitX, limitY = len(land), len(land[0])
    arr = [0] * limitY

    
    def bfs(x, y):
        queue = deque()
        check = set()
        queue.append((x, y))
        tmp = 1
        land[x][y] = 0
        check.add(y)
        
        while queue:
            x, y = queue.popleft()
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                if nx < 0 or nx >= limitX or ny < 0 or ny >= limitY:
                    continue
                if not land[nx][ny]:
                    continue
                land[nx][ny] = 0
                check.add(ny)
                tmp += 1
                queue.append((nx, ny))

        for c in check:
            arr[c] += tmp

    
    for x in range(limitX):
        for y in range(limitY):
            if land[x][y] == 1:
                bfs(x, y)

    return max(arr)