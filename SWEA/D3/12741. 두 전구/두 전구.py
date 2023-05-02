# 12741. 두 전구 <D3>

tc = int(input())

answer = []
for t in range(1, tc + 1):
    a, b, c, d = map(int, input().split())
    t1, t2 = max(a, c), min(b, d)
    time = t2 - t1
    if time < 0:
        time = 0
    answer.append(time)

for ans in range(len(answer)):
    print(f'#{ans+1} {answer[ans]}')
    