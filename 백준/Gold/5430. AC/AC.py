# 5430. AC [G5]

from collections import deque

test_case = int(input())

for _ in range(test_case):
    cmd = str(input())
    num = int(input())
    arr = input()[1:-1].split(',')

    queue = deque(arr)

    flag = 0

    if num == 0:
        queue = []

    for s in cmd:
        if s == 'R':
            flag += 1
        elif s == 'D':
            if len(queue) == 0:
                print('error')
                break
            else:
                if flag % 2 == 0:
                    queue.popleft()
                else:
                    queue.pop()
    else:
        if flag % 2 == 0:
            print('[' + ','.join(queue) + ']')
        else:
            queue.reverse()
            print('[' + ','.join(queue) + ']')