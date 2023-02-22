# 1966. 프린터 큐 [S3]

from collections import deque

test_case = int(input())
for _ in range(test_case):
    N, M = map(int, input().split())
    arr = deque(list(map(int, input().split())))
    idx = deque(list(range(N)))
    cnt = 0

    while arr:
        if arr[0] == max(arr):
            cnt += 1
            arr.popleft()
            pop_idx = idx.popleft()
            if pop_idx == M:
                print(cnt)
        else:
            arr.append(arr.popleft())
            idx.append(idx.popleft())