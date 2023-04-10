# 2805. 농작물 수확하기 <D3>

tc = int(input())
for t in range(1, tc + 1):
    ans = 0
    n = int(input())
    if n > 1:
        for i in range(n//2, 0, -1):
            tmp = list(map(int, input()))
            ans += sum(tmp[i:-i])
    tmp = list(map(int, input()))
    ans += sum(tmp)
    if n > 1:
        for j in range(1, n//2 + 1):
            tmp = list(map(int, input()))
            ans += sum(tmp[j:-j])
    print(f'#{t} {ans}')