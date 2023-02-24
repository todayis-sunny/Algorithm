# 6603. 로또 [S2]

from itertools import combinations

while True:
    numbers = list(map(int, input().split()))

    k = numbers[0]
    S = numbers[1:]

    for i in combinations(S, 6):
        print(*i)

    if numbers[0] == 0:
        break
    print()