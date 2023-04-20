# 4466. 최대 성적표 만들기 <D3>

tc = int(input())

for t in range(1, tc + 1):
    n, k = map(int, input().split())
    scores = sorted(list(map(int, input().split())))
    ans = sum(scores[-k:])
    print(f'#{t} {ans}')
