# 3314. 보충학습과 평균 <D3>

tc = int(input())

for t in range(1, tc + 1):
    score = list(map(int, input().split()))
    avg = 0
    for s in score:
        if s < 40:
            s = 40
        avg += s // 5
    print(f'#{t} {avg}')