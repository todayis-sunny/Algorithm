# 1959. 두 개의 숫자열 <D2>

for t in range(1, int(input()) + 1):
    n, m = map(int, input().split())
    ai = list(map(int, input().split()))
    bj = list(map(int, input().split()))
    answer = -1e9

    if n <= m:
        for a in range(0, m-n+1):
            tmp = 0
            for i in range(n):
                tmp += ai[i] * bj[i+a]
            if answer < tmp:
                answer = tmp
    else:
        for b in range(0, n-m+1):
            tmp = 0
            for j in range(m):
                tmp += ai[j+b] * bj[j]
            if answer < tmp:
                answer = tmp

    print(f"#{t} {answer}")
