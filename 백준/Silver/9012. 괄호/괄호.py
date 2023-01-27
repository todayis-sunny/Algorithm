# 9012. 괄호 [S4]

N = int(input())

for i in range(N):
    vsp = str(input())
    flag = 0
    for j in range(len(vsp)):
        if flag <= -1:
            break
        elif vsp[j] == '(':
            flag += 1
        else:
            flag -= 1
    if flag == 0:
        print('YES')
    else:
        print('NO')