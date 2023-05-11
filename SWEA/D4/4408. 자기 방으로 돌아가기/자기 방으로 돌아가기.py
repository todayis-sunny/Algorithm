# 4408. 자기 방으로 돌아가기 <D4>

# 홀수 방, 짝수 방
room = [r for r in range(400)]
for t in range(1, int(input()) + 1):
    n = int(input())
    # 복도
    corridor = [0 for _ in range(200)]
    # 이동할 목록
    arr = []
    for _ in range(n):
        r1, r2 = map(int, input().split())
        r1 -= 1
        r2 -= 1
        a, b = min(r1, r2), max(r1, r2)
        c1, c2 = room.index(a) // 2, room.index(b) // 2
        for i in range(c1, c2+1):
            corridor[i] += 1

    print(f'#{t} {max(corridor)}')


