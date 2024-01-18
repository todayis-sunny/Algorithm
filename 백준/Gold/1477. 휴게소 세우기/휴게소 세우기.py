# 01447. [G4] 휴게소 세우기

def check(N1, N2):
    if N1 % N2 >= 1:
        return N1 // N2 + 1
    return N1 // N2


N, M, L = map(int, input().split())

arr = list(map(int, input().split()))
arr.append(0)
arr.append(L)
arr = sorted(arr)

distance = [] # 고정 휴게소 사이거리
memo = [] # 휴게소를 설치할 때마다 계속 변경되는 거리


for i in range(len(arr) - 1):
    distance.append(arr[i + 1] - arr[i])


cnt = [1] * len(distance)
distance = sorted(distance)

for i in range(len(distance)):
    memo.append(distance[i])


for i in range(M):
    mdIdx = memo.index(max(memo))
    cnt[mdIdx] += 1
    memo[mdIdx] = check(distance[mdIdx], cnt[mdIdx])

print(max(memo))



