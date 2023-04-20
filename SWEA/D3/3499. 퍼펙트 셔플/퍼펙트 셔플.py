# 3499. 퍼펙트 셔플 <D3>

tc = int(input())

for t in range(1, tc + 1):
    n = int(input())
    arr = list(map(str, input().split()))
    k = (n + 1) // 2
    c = 1
    for i in range(k, len(arr)):
        arr.insert(c, arr.pop(i))
        c += 2
    print(f'#{t}', *arr)
