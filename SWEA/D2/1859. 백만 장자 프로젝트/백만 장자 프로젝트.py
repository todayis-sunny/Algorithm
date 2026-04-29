# 1859. 백만 장자 프로젝트 <D2>

TC = int(input())

for tc in range(1, TC + 1):
    money = 0
    n = int(input())
    arr = list(map(int, input().split()))
    max_num = -1

    for j in reversed(range(n)):
        if max_num < arr[j]:
            max_num = arr[j]

        if arr[j] < max_num:
            money += max_num - arr[j]

    print('#' + str(tc) + ' ' + str(money))