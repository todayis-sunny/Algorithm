# 5688. 세제곱근을 찾아라 <D3>

key = [0]
for i in range(1, 10**6 + 1):
    key.append(i**3)

for t in range(1, int(input()) + 1):
    n = int(input())

    if n in key:
        print(f'#{t} {key.index(n)}')
    else:
        print(f'#{t} -1')