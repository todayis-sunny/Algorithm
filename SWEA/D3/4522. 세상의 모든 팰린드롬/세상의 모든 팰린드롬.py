# 4522. 세상의 모든 팰린드롬 <D3>

for t in range(1, int(input()) + 1):
    word = str(input())
    half = len(word) // 2
    if half % 2 == 0:
        a = word[:half]
        b = word[-1:-(half + 1):-1]

    else:
        a = word[:half]
        b = word[-1:-(half + 1):-1]

    for i in range(half):
        if a[i] == '?' or b[i] == '?':
            continue
        elif a[i] != b[i]:
            print(f'#{t} Not exist')
            break
    else:
        print(f'#{t} Exist')
