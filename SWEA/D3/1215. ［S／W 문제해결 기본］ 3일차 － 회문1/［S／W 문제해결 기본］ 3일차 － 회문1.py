# 1215. 회문1 <D3>

tc = 10
for t in range(1, tc + 1):
    n = int(input())
    arr1 = [list(map(str, input())) for _ in range(8)]
    arr2 = list(zip(*arr1))

    ans = 0
    for i in range(8):
        for j in range(9-n):
            tmp11 = ''.join(arr1[i][j:j+n])
            tmp12 = tmp11[::-1]

            tmp21 = ''.join(arr2[i][j:j + n])
            tmp22 = tmp21[::-1]
            if tmp11 == tmp12:
                ans += 1
            if tmp21 == tmp22:
                ans += 1

    print(f'#{t} {ans}')