from collections import deque

INF = 1e9
def solution(m, n, h, w, drops):
    # dp 크기 지정
    time = [[INF] * n for _ in range(m)]
    
    # 비오는 지점 체크
    for i in range(len(drops)):
        x, y = drops[i]
        time[x][y] = i + 1
    
    # 슬라이딩 윈도우
    # 가로
    for r in range(m):
        dq = deque()
        # 길이의 최소 기준 인덱스
        std = w - 1
        for c in range(n):
            # 윈도우 범위를 벗어난 인덱스 제거
            while dq and dq[0] <= c - w:
                dq.popleft()
                
            # 최소값만 덱에 저장
            while dq and time[r][c] <= time[r][dq[-1]]:
                dq.pop()

            # 현재 인덱스 삽입
            dq.append(c)
            
            # 길이 조건이 만족하면 기록
            if c >= std:
                time[r][c - std] = time[r][dq[0]]
                
    # 세로
    for c in range(n):
        dq = deque()
        # 길이의 최소 기준 인덱스
        std = h - 1
        for r in range(m):
            # 윈도우 범위를 벗어난 인덱스 제거
            while dq and dq[0] <= r - h:
                dq.popleft()
            
            # 최소값만 덱에 저장
            while dq and time[r][c] <= time[dq[-1]][c]:
                dq.pop()
                
            # 현재 인덱스 삽입
            dq.append(r)
            
            # 길이 조건이 만족하면 기록
            if r >= std:
                time[r - std][c] = time[dq[0]][c]
    
    # 선인장을 둘수 있는 좌상단 모서리 좌표
    row = m - h + 1
    col = n - w + 1
    result = [0, 0]
    goal = 0
    for r in range(row):
        for c in range(col):
            if time[r][c] > goal:
                result[0], result[1] = r, c
                goal = time[r][c]

    return result
