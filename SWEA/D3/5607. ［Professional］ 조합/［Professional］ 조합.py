# 5607. 조합 <D3>

p = 1234567891
tc = int(input())


# 팩토리얼 값 계산(나머지 연산 적용)
def factorial(num):
    n = 1
    for i in range(2, num + 1):
        n = (n * i) % p
    return n


# 거듭제곱 계산(나머지 연산 적용)
def square(n, k):
    if k == 0:
        return 1
    elif k == 1:
        return n

    tmp = square(n, k//2)
    if k % 2:
        return tmp * tmp * n % p
    else:
        return tmp * tmp % p

for t in range(1, tc + 1):
    n, r = map(int, input().split())

    top = factorial(n)
    bot = factorial(n-r) * factorial(r) % p
    print(f'#{t} {top * square(bot, p-2) % p}')