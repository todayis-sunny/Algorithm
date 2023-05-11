# 3234. 준환이의 양팔저울 <D4>

from itertools import permutations


# 좌측에 두는 행위 : + | 우측에 두는 행위 : -
def dfs(idx, l, r):
    global case
    # 마지막 순서 종료
    if idx == n:
        case += 1
        return
    # 우측에 추를 몰아 넣어도 괜찮은 경우 -> 2 ** 남은 추의 갯수
    if l >= limit - l:
        case += 2**(n - idx)
        return
    
    dfs(idx+1, l+arr[idx], r)
    if l >= r+arr[idx]:
        dfs(idx+1, l, r+arr[idx])


for t in range(1, int(input()) + 1):
    n = int(input())
    weight = list(map(int, input().split()))
    limit = sum(weight)
    case = 0
    for arr in permutations(weight, n):
        dfs(0, 0, 0)

    print(f'#{t} {case}')
