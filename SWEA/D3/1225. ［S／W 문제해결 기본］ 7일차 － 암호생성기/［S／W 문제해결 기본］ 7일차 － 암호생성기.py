# 1225. 암호생성기 <D3>
from collections import deque

for t in range(1, 11):
    tc = int(input())
    arr = list(map(int, input().split()))

    queue = deque(arr)
    flag = True
    k = 1
    while True:
        tmp = queue.popleft()
        if tmp - k > 0:
            queue.append(tmp - k)
        else:
            queue.append(0)
            break

        k += 1
        if k > 5:
            k = 1
    print(f'#{t}', *queue, sep=' ')
