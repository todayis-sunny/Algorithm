# 14361. 숫자가 같은 배수

tc = int(input())

for t in range(1, tc + 1):
    num = int(input())
    length = len(str(num))
    k = 1
    while len(str(k*num)) == length:
        k += 1
        if sorted(str(k*num)) == sorted(str(num)):
            print(f'#{t} possible')
            break

    else:
        print(f'#{t} impossible')
