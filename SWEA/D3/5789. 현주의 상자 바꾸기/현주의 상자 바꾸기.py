# 5789. 현주의 상자 바꾸기 <D3>

tc = int(input())

for t in range(1, tc+1):
    n, q = map(int, input().split())
    arr = [0] * n
    for i in range(1, q+1):
        l, r = map(int, input().split())
        for j in range(l-1, r):
            arr[j] = i
    print(f'#{t}', *arr, sep=' ')