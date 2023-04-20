# 15612. 체스판 위의 룩 배치 <D3>

tc = int(input())

for t in range(1, tc + 1):
    arr = [str(input()) for _ in range(8)]
    check = [False] * 8
    flag = True
    for a in arr:
        if a.count('O') > 1:
            flag = False
            break
        elif a.count('O') == 1:
            if not check[a.index('O')]:
                check[a.index('O')] = True
            else:
                flag = False
                break
    if False in check:
        flag = False
        
    if flag:
        print(f'#{t} yes')
    else:
        print(f'#{t} no')
