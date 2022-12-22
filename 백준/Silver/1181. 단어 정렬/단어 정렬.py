# 1181. 단어 정렬

import sys

N = int(sys.stdin.readline())
arr = []

for n in range(N):
    arr.append(sys.stdin.readline().strip())
unique = set(arr)
arr = list(unique)
arr.sort()
arr.sort(key = len)

for s in arr:
    print(s)