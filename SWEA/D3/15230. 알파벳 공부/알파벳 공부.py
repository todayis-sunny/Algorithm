# 15230. 알파벳 공부 <D3>

tc = int(input())

for t in range(1, tc + 1):
    words = str(input())
    k = 97
    ans = 0
    for w in words:
        if ord(w) == k:
            ans += 1
        else:
            break
        k += 1
    print(f'#{t} {ans}')
