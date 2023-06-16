from collections import deque

dx = [1, 0, -1]
dy = [0, 1, -1]

def solution(n):
    arr = [[0] * n for _ in range(n)]
    limit = n*(1+n) // 2
    x, y, i = 0, 0, 0
    for num in range(1, limit+1):
        arr[x][y] = num
        if (x + dx[i]) >= n or (y + dy[i]) >= n:
            i += 1
            i %= 3
        elif arr[x + dx[i]][y + dy[i]] != 0:
            i += 1
            i %= 3
        x = x + dx[i]
        y = y + dy[i]
    
    answer = []
    for i in range(n):
        answer += arr[i][:i+1]
    
    return answer