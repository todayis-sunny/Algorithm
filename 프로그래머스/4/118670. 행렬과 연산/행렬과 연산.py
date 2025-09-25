from collections import deque

SR = "ShiftRow"
RT = "Rotate"

def solution(rc, operations):
    # 행과 열 길이
    maxR = len(rc)
    maxC = len(rc[0])
    
    # ShiftRow 연산을 위해 행별로 관리
    rows = deque(deque(row[1:-1]) for row in rc)
    # Rotate 연산을 위해 바깥쪽 열을 관리
    outCols = [deque(rc[r][0] for r in range(maxR)),
              deque(rc[r][-1] for r in range(maxR))]
    
    
    # 연산 루프
    for op in operations:
        if op == SR:
            # 최하단 row를 최상단row 위로 이동
            rows.appendleft(rows.pop())
            # 바깥 col도 원소이동
            outCols[0].appendleft(outCols[0].pop())
            outCols[1].appendleft(outCols[1].pop())
        else:
            # 우측 col의 최하단 원소 -> 최하단 row의 우측 원소
            rows[-1].append(outCols[1].pop())
            # 최하단 row의 좌측 원소 -> 좌측 col의 최하단 원소
            outCols[0].append(rows[-1].popleft())
            # 좌측 col의 최상단 원소 -> 최상단 row의 좌측 원소
            rows[0].appendleft(outCols[0].popleft())
            # 최상단 row의 우측 원소 -> 우측 col의 최상단 원소
            outCols[1].appendleft(rows[0].pop())

    answer = []
    for r in range(maxR):
        answer.append([outCols[0][r]] + list(rows[r]) + [outCols[1][r]])
    return answer