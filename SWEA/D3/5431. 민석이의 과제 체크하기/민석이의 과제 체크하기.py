# 5431. 민석이의 과제 체크하기 <D3>

tc = int(input())

for t in range(1, tc+1):
    n, k = map(int, input().split())
    arr = [i for i in range(1, n+1)]
    hwk = list(map(int, input().split()))
    print(f'#{t}', *(list(set(arr) - set(hwk))), sep=' ')
