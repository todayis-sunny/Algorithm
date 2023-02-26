# 1182. 부분수열의 합

from itertools import combinations

N, S = map(int, input().split())

numbers = list(map(int, input().split()))

cnt = 0
for n in range(1, N+1):
    for arr in (combinations(numbers, n)):
        if sum(arr) == S:
            cnt += 1
print(cnt)