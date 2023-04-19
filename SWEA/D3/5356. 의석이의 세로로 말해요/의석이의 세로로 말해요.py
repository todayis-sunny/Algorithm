# 5356. 의석이의 세로로 말해요 <D3>

tc = int(input())

for t in range(1, tc + 1):
    arr = [list(map(str, input())) for _ in range(5)]

    max_len = 0
    for element in arr:
        if len(element) > max_len:
            max_len = len(element)

    for i in range(5):
        for _ in range(max_len - len(arr[i])):
            arr[i].append('')

    arr = list(zip(*arr))
    print(f'#{t} ', end='')
    for a in arr:
        print(*a, sep='', end='')
    print()