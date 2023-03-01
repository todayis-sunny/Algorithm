# 1929. 소수 구하기 [S3]

min_num, max_num = map(int, input().split())

for num in range(min_num, max_num + 1):
    if num == 1:
        continue
    for k in range(2, int(num ** 0.5) + 1):
        if num % k == 0:
            break
    else:
        print(num)