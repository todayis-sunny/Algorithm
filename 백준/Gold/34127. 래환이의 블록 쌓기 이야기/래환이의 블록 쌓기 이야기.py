# 입력
N = int(input())
building = [0] + list(map(int, input().split()))
result = [0] * (N + 1)
# 로직
prev = -1
for i in range(1, N + 1):
    prev = building[i - 1] + result[i - 1]
    increment = (prev + 1) - building[i]
    if increment == 0: continue
    result[i] = increment + ((i ^ abs(increment)) & 1)

# 남는 부분 마지막 빌딩에 추가
total = sum(result)
if total != 0:
    increment = - (int(total / 2) * 2)
    result[-1] += increment
# 출력
if building[-1] + result[-1] > building[-2] + result[-2]:
    print("YES")
    print(*result[1:])
else:
    print("NO")
