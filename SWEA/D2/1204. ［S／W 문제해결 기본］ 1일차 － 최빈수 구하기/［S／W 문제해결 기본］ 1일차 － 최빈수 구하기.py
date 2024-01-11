# 1204. [S/W 문제해결 기본] 1일차 - 최빈수 구하기 <D2>

for t in range(1, int(input()) + 1):
    n = int(input())
    scores = list(map(int, input().split()))
    count = [0] * 101
    for s in scores:
        count[s] += 1

    max_cnt = max(count)

    for i in range(100, -1, -1):
        if max_cnt == count[i]:
            print(f"#{t} {i}")
            break
