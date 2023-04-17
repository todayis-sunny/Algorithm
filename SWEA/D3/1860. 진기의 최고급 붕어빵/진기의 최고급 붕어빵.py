# 1860. 진기의 최고급 붕어빵 <D3>

from collections import deque
tc = int(input())

for t in range(1, tc + 1):
    # n : 사람 | m : 붕어빵 생산 소요 시간 | k : 붕어빵 생산 개수
    n, m, k = map(int, input().split())
    guest = deque(sorted(list(map(int, input().split()))))
    bun = 0
    temp = guest.popleft()
    if guest:
        last = max(guest)
    else:
        last = temp
    flag = True
    for i in range(0, last+1):
        # 0초를 제외한 m초 시간 이후 붕어빵 생산
        if i % m == 0 and i != 0:
            bun += k
        # 붕어빵 갯수가 (대기손님 1명 + 예악손님 x명)이상일 경우 종료
        if bun >= len(guest) + 1:
            print(f'#{t} Possible')
            break
        # 손님이 오는 시간에 붕어빵 1개 소거, 이때 붕어빵이 부족하면 종료
        # 같은 시간대 오는 손님이 없어질 때까지 반복
        while i == temp:
            bun -= 1
            if bun < 0:
                print(f'#{t} Impossible')
                flag = False
                break
            if guest:
                temp = guest.popleft()
        if not flag:
            break
