# 9095. 1,2,3 더하기 [S3]

tc = int(input())

def fun(n):
    if n == 1:
        return 1
    elif n == 2:
        return 2
    elif n == 3:
        return 4
    else:
        return fun(n-1) + fun(n-2) + fun(n-3)

for _ in range(tc):
    n = int(input())
    print(fun(n))