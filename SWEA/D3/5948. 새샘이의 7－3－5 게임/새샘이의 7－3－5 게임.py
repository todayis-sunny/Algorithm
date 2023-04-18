# 5948. 새샘이의 7-3-5 게임 <D3>

from itertools import combinations as cb

tc = int(input())

for t in range(1, tc + 1):
    tmp = list(map(int, input().split()))
    arr = []
    for i, j, k in cb(tmp, 3):
        arr.append(i+j+k)
    arr = sorted(list(set(arr)))
    print(f'#{t} {arr[-5]}')
