# 10816. 숫자 카드 2 [S4]
import sys

N = int(sys.stdin.readline())
N_arr = sorted(list(map(int, input().split())))

M = int(sys.stdin.readline())
M_arr = list(map(int, input().split()))

cnt = {}
for i in N_arr:
    if i in cnt:
        cnt[i] += 1
    else:
        cnt[i] = 1

for i in M_arr:
    if i in cnt:
        print(cnt[i], end=' ')
    else:
        print(0, end=' ')