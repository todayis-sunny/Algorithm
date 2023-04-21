# 10761. 신뢰 <D3>

from collections import deque

tc = int(input())

for t in range(1, tc + 1):
    data = list(map(str, input().split()))
    # 총 소요 시간
    ans = 0
    # 순서 turn, 버튼 순서 고려한 위치
    turn = deque()
    o_map = deque()
    b_map = deque()
    # 대기열
    b_que = deque()
    o_que = deque()
    # 눌러야 하는 버튼의 갯수
    goal = int(data[0])
    # o, b의 현재 위치
    ox = 1
    bx = 1

    for i in range(1, len(data), 2):
        turn.append(data[i])
        if data[i] == 'O':
            o_map.append(int(data[i + 1]))
        else:
            b_map.append(int(data[i + 1]))
    # 단위 시간 버튼 입력 여부
    flag = False
    # 현재 까지 누른 버튼의 갯수
    target = 0

    while goal != target:
        sec = 0
        btn = turn.popleft()
        # 다음 목표 위치 받아오기
        if o_map and not o_que:
            o_que.append(o_map.popleft())
        if b_map and not b_que:
            b_que.append(b_map.popleft())

        # 버튼이 O, B에 따라 행동
        if btn == 'O' and o_que:
            sec += abs(o_que[0] - ox) + 1
            ans += sec
            target += 1
            ox = o_que.popleft()
            if b_que:
                if abs(b_que[0] - bx) <= sec:
                    bx = b_que[0]
                else:
                    if bx < b_que[0]:
                        bx += sec
                    else:
                        bx -= sec

        elif btn == 'B' and b_que:
            sec += abs(b_que[0] - bx) + 1
            ans += sec
            target += 1
            bx = b_que.popleft()
            if o_que:
                if abs(o_que[0] - ox) <= sec:
                    ox = o_que[0]
                else:
                    if ox < o_que[0]:
                        ox += sec
                    else:
                        ox -= sec

    print(f'#{t} {ans}')
