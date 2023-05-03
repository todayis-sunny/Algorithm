# 11856. 반반 <D3>

tc = int(input())

for t in range(1, tc + 1):
    words = str(input())
    if len(words) == len(list(set(words))) * 2:
        print(f'#{t} Yes')
    else:
        print(f'#{t} No')