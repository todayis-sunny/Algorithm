# 13547. 팔씨름 <D3>

tc = int(input())

for t in range(1, tc + 1):
    result = list(map(str, input()))
    if result.count('x') <= 7:
        print(f'#{t} YES')
    else:
        print(f'#{t} NO')
