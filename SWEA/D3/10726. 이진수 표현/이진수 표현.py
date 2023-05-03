# 10726. 이진수 표현 <D3>

tc = int(input())

for t in range(1, tc + 1):
    n, m = map(int, input().split())

    for _ in range(n):
        if m % 2 == 0:
            print(f'#{t} OFF')
            break
        m //= 2
    else:
        print(f'#{t} ON')