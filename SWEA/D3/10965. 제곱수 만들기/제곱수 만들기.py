# 10965. 제곱수 만들기 <D3>

k = int((10**7) ** 0.5)
primes = [2]
for i in range(3, k, 2):
    for p in primes:
        if not i % p:
            break
    else:
        primes.append(i)

result = []

for t in range(1, int(input()) + 1):
    a = int(input())
    ans = 1
    if a ** 0.5 != int(a ** 0.5):
        for p in primes:
            cnt = 0
            while a % p == 0:
                a //= p
                cnt += 1
            if cnt % 2 == 1:
                ans *= p
            if a == 1 or p > a:
                break
        if a > 1:
            ans *= a

    result.append(f'#{t} {ans}')

for r in result:
    print(r)
