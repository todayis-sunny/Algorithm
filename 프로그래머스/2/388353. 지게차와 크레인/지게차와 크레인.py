def fork(storage, box): # 지게차
    dx, dy = [0, 0, 1, -1], [1, -1, 0, 0]
    index = []
    
    for i in range(1, len(storage)-1):
        for j in range(1, len(storage[0])-1):
            if storage[i][j] == box:
                for k in range(4):
                    nx, ny = i + dx[k], j + dy[k]
                    if storage[nx][ny] == "0":  # 지게차로 꺼낼 수 있으면 (외부쪽 박스)
                        index.append((i, j))    # 위치 저장(바로 꺼내면 옆에있는 박스도 꺼낼 수 있게 됨)
                        break
    
    for i, j in index:  # 저장된 박스 꺼내기
        storage[i][j] = "0"
        isOutside(storage, i, j) # 주변에 빈 공간있으면 외부랑 연결

def crane(storage, box): # 크레인
    for i in range(1, len(storage)-1):
        for j in range(1, len(storage[0])-1):
            if storage[i][j] == box:
                storage[i][j] = "1"
                isOutside(storage, i, j) # 주변에 빈 공간있으면 외부랑 연결

def isOutside(storage, x, y):   # 박스 꺼냈을 때 외부랑 연결된 공간 찾아서 연결
    dx, dy = [0, 0, 1, -1], [1, -1, 0, 0]
    outside = False

    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if storage[nx][ny] == "0":  # 외부랑 연결되어 있으면
            storage[x][y] = "0"
            outside = True         
            break
    
    if outside:
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if storage[nx][ny] == "1":  # 빈 공간을 외부랑 연결 "1" -> "0"
                storage[nx][ny] = "0"
                isOutside(storage, nx, ny)  # 붙어있는 빈 공간을 더 찾기

def solution(storage, requests):
    answer = 0
    # 0 = 외부랑 연결된 곳, 1 = 사방이 막힌 빈 공간
    storage = [list("0" + i + "0") for i in storage]    # 테두리를 "0"으로 채우기
    storage.insert(0, list("0" * len(storage[0])))
    storage.append(list("0" * len(storage[0])))

    for q in requests:
        if len(q) == 1:
            fork(storage, q)
        else:
            crane(storage, q[0])
    
    for i in range(1, len(storage)-1):
        for j in range(1, len(storage[0])-1):
            if storage[i][j] not in ["0", "1"]:
                answer += 1
    
    return answer