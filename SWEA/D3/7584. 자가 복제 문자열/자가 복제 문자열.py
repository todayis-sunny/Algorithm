# 7584. 자가 복제 문자열 <D3>#

tc = int(input())

for t in range(1, tc + 1):
    k = int(input()) - 1
    n = 0
    while k >= 0:
        if k % 2 == 1:
            k = (k-1) // 2
        if k % 4 == 0:
            n = 0
            break
        elif k % 2 == 0:
            n = 1
            break

    print(f'#{t} {n}')