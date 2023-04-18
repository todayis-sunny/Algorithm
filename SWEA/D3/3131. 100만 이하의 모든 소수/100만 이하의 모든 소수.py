# 3131. 100만 이하의 모든 소수 <D3>

n = 10**6
arr = [True] * (n + 1)

for i in range(2, int(n**0.5)+1):
    if arr[i]:
        for j in range(i*2, n+1, i):
            arr[j] = False

for i in range(2, len(arr)):
    if arr[i]:
        print(i, end=' ')
