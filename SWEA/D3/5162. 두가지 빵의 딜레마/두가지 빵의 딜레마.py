# 5162. 두가지 빵의 딜레마 <D3>

for t in range(1, int(input()) + 1):
    a, b, c = map(int, input().split())
    print(f'#{t} {c//min(a, b)}')
    