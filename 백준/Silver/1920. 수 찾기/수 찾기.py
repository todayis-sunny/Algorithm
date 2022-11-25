# 1920. 수 찾기 [S4]

N = int(input())
N_arr = set(map(int, input().split()))

M = int(input())
M_arr = list(map(int, input().split()))

for i in range(M):
    if M_arr[i] in N_arr:
        print(1)
    else:
        print(0)