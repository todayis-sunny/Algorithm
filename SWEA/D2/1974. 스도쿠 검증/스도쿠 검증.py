# 1974. 스도쿠 검증 <D2>

def sudoku(arr):
    cnt = 0
    for i in range(9):
        if len(set(arr[i])) != 9:  # 행 중복 검사
            return 0
        cols = [row[i] for row in arr]
        if len(set(cols)) != 9:  # 열 중복 검사
            return 0

    for i in range(0, 9, 3):
        for j in range(0, 9, 3):  # 사각형 탐지
            squar = []
            for k in range(3):
                for l in range(3):
                    squar.append(arr[i + k][j + l])
        if len(set(squar)) != 9:
            return 0
    return 1


for t in range(1, int(input()) + 1):
    arr = []
    for i in range(9):
        num = list(map(int, input().split()))
        arr.append(num)

    print(f"#{t} {sudoku(arr)}")