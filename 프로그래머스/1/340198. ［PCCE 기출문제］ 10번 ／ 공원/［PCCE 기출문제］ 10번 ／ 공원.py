def solution(mats, park):
    # 큰 돗자리부터 검사할 예정
    mats.sort(reverse = True)
    # 행, 열
    row = len(park)
    col = len(park[0])
    # 큰 돗자리부터 검사
    for m in mats:
        for r in range(row - (m -1)):
            for c in range(col - (m - 1)):
                # 빈 자리가 아닐경우 스킵
                if park[r][c] != '-1':
                    continue
                # 빈자리인 경우 탐색
                for x in range(r, r + m):
                    for y in range(c, c + m):
                        # 빈 자리가 아닐경우 탈출
                        if park[x][y] != '-1':
                            break
                    # 한 줄 빈자리 확인
                    else:
                        continue
                    # 빈자리가 없는 경우 탈출
                    break
                # 모두 빈자리 확인
                else:
                    return m
                            
    return -1
