# 1240. 단순 2진 암호코드 <D3>

tc = int(input())
dic = {'0001101': 0, '0011001': 1,
       '0010011': 2, '0111101': 3,
       '0100011': 4, '0110001': 5,
       '0101111': 6, '0111011': 7,
       '0110111': 8, '0001011': 9}
for t in range(1, tc + 1):
    n, m = map(int, input().split())
    arr = [str(input()) for _ in range(n)]
    for i in range(n):
        if not arr[i].count('1'):
            continue
        for j in range(1, m + 1):
            if arr[i][-j] == '1':
                b = m - j
                a = b - 56
                break
        tmp = arr[i][a + 1:b + 1]
        break
    pwd = []
    for k in range(0, 56, 7):
        pwd.append(dic[tmp[k:k + 7]])
    ans = sum(pwd[0::2]) * 3 + sum(pwd[1::2])

    if not ans % 10:
        print(f'#{t} {sum(pwd)}')
    else:
        print(f'#{t} 0')