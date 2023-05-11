# 3459. 승자 예측하기 <D4>

for t in range(1, int(input()) + 1):
    n = int(input())
    turn = -1
    n //= 2
    n += 1
    while n > 1:
        if turn > 0:
            n = (n+1) // 2
        else:
            n //= 2
        turn *= -1
    if turn > 0:
        print(f'#{t} Alice')
    else:
        print(f'#{t} Bob')