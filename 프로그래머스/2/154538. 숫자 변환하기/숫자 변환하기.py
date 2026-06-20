from collections import deque

def solution(x, y, n):
    visited = set()
    dq = deque()
    for k in range(3):
        # 연산 횟수, x, 연산 종류 
        dq.append((0, x, k))
        visited.add(x)
    while dq:
        result, x, k = dq.popleft()
        if x == y:
            return result
        result += 1
        if k == 0:
            x += n
        elif k == 1:
            x *= 2
        else:
            x *= 3
        if x == y:
            return result
        if x not in visited and x < y:
            visited.add(x)
            for k in range(3):
                dq.append((result, x, k))
    return -1