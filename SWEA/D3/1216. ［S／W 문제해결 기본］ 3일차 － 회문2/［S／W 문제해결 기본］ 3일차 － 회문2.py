# 1216. 회문2 <D3>

tc = 10

for t in range(1, tc + 1):
    n = int(input())
    arr1 = [list(map(str, input())) for _ in range(100)]
    arr2 = list(zip(*arr1))
    ans = 0
    flag = False
    for l in range(100, 0, -1):

        for i in range(100):
            for j in range(101-l):
                tmp1 = ''.join(arr1[i][j:j+l])
                if tmp1 == tmp1[::-1]:
                    ans = l
                    flag = True
                    break
                tmp2 = ''.join(arr2[i][j:j+l])
                if tmp2 == tmp2[::-1]:
                    ans = l
                    flag = True
                    break
            if flag:
                break
        if flag:
            break

    print(f'#{t} {ans}')

