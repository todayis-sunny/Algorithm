# 4613. 러시아 국기 같은 깃발 <D4>

for t in range(1, int(input()) + 1):
    n, m = map(int, input().split())
    arr = [list(map(str, input())) for _ in range(n)]
    ans = 0
    # 최상단과 최하단 줄은 무조건 W, R
    ans += m - arr[0].count('W')
    ans += m - arr[-1].count('R')
    cnt = 1e9
    cut = [i for i in range(1, n-1)]
    # B의 줄의 갯수 (최소 : 1 | 최대 : n-2)
    for i in range(1, n-1):
        for j in range(n-1-i):
            tmp = 0
            w_cut = cut[:j]
            b_cut = cut[j:j+i]
            r_cut = cut[j+i:]
            for w in w_cut:
                tmp += (m - arr[w].count('W'))
            for b in b_cut:
                tmp += (m - arr[b].count('B'))
            for r in r_cut:
                tmp += (m - arr[r].count('R'))
            cnt = min(cnt, tmp)
    ans += cnt
    print(f'#{t} {ans}')