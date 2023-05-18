# 4676. 늘어지는 소리 만들기 <D3>

for t in range(1, int(input()) + 1):
    words = list(map(str, input()))
    L = len(words)
    h = int(input())
    location = list(map(int, input().split()))
    ans = ''
    for w in range(L+1):
        s = '-' * location.count(w)
        ans += s
        if w != L:
            ans += words[w]
    print(f'#{t} {ans}')
