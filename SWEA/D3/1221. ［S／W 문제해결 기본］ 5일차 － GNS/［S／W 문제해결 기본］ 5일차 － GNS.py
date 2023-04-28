# 1221. GNS <D3>

words = ['ZRO', 'ONE', 'TWO', 'THR', 'FOR',
         'FIV', 'SIX', 'SVN', 'EGT', 'NIN']

tc = int(input())

for t in range(1, tc + 1):
    tmp = str(input())
    arr = list(map(str, input().split()))
    ans = ''
    print(f'#{t}')
    for w in words:
        ans += (w + ' ') * arr.count(w)
    print(ans)