# 1208. Flatten <D3>

for i in range(1, 11):
    dump = int(input())
    arr = list(map(int, input().split()))
    for _ in range(dump):
        arr.sort()
        arr[-1] -= 1
        arr[0] += 1
    print(f'#{i} {max(arr)-min(arr)}')