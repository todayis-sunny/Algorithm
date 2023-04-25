# 13428. 숫자 조작 <D3>

from itertools import combinations

tc = int(input())

for t in range(1, tc + 1):
    num = list(x for x in input())
    target = [i for i in range(len(num))]

    min_num, max_num = int(''.join(num)), int(''.join(num))

    for value in combinations(target, 2):
        i, j = value
        num[i], num[j] = num[j], num[i]
        if num[0] == '0':
            num[i], num[j] = num[j], num[i]
            continue
        if int(''.join(num)) < min_num:
            min_num = int(''.join(num))
        if int(''.join(num)) > max_num:
            max_num = int(''.join(num))
        num[i], num[j] = num[j], num[i]

    print(f'#{t} {min_num} {max_num}')
