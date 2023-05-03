# 5549. 홀수일까 짝수일까 <D3>

tc = int(input())

for t in range(1, tc + 1):
    num = str(input())
    if int(num) % 2 == 0:
        print(f'#{t} Even')
    else:
        print(f'#{t} Odd')
