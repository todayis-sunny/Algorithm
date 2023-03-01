# 1978. 소수 찾기 [S5]

def sol(n):
    cnt = 0
    for i in range(1, n + 1):
        if n % i == 0:
            cnt += 1
    if cnt == 2:
        return True
    else:
        return False


N = int(input())

arr = list(map(int, input().split()))

k = 0
for num in arr:
    if sol(num):
        k += 1

print(k)