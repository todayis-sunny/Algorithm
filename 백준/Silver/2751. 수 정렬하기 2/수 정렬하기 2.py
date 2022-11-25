# 2751. 수 정렬하기 2 [S2]

N = int(input())
arr = []
for n in range(N):
    arr.append(int(input()))

arr.sort()
for n in range(N):
    print(arr[n])