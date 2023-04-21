# 10912. 외로운 문자 <D3>

tc = int(input())

for t in range(1, tc + 1):
    tmp = str(input())
    ans = ''
    for i in range(0, 26):
        k = i + 97
        if tmp.count(chr(k)) % 2 == 1:
            ans += chr(k)
    if len(ans) == 0:
        print(f'#{t} Good')
    else:
        print(f'#{t} {ans}')
