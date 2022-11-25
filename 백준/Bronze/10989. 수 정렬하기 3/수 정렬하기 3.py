# 10989. 수 정렬하기 3 [B1]
import sys

N = int(input())
arr = [0] * 10001
for n in range(N):
    num = int(sys.stdin.readline())

    arr[num] += 1

for i in range(10001):
    if arr[i] != 0:
        for j in range(arr[i]):
            print(i)