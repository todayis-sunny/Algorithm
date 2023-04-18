# 1234. 비밀번호 <D3>

for t in range(1, 11):
    n, pwd = map(str, input().split())

    ans = ''
    for p in pwd:
        ans += p
        if len(ans) >= 2:
            if ans[-2] == ans[-1]:
                ans = ans[:-2]
    print(f'#{t} {ans}')
