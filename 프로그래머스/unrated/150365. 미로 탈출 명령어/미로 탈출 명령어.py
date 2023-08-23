def solution(n, m, x, y, r, c, k):
    min_distance = abs(x-r) + abs(y-c)
    if (k - min_distance) % 2 != 0 or k < min_distance:
        return "impossible"
    
    # 필요한 최소 이동 거리
    xd = r-x
    yd = c-y
    
    # 사전순 | d:하, l:좌, r:우, u:상
    direction = ['d', 'l', 'r', 'u']
    dx = [1, 0, 0, -1]
    dy = [0, -1, 1, 0]
    
    answer = ''
    while len(answer) < k:
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            # 격자를 벗어나면
            if nx < 1 or nx > n or ny < 1 or ny > m:
                continue
            # 최소거리가 충족이 안 된다면
            elif k - len(answer) - 1 < abs(nx-r) + abs(ny-c):
                continue
            else:
                answer += direction[i]
                x, y = nx, ny
                break

    return answer