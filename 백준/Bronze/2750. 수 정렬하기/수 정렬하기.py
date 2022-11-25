# 2750. 수 정렬하기 [B2]

N = int(input())
arr = []
for n in range(N):
    arr.append(int(input()))

arr.sort()
for n in range(N):
    print(arr[n])