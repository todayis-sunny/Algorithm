# 4111. 무선 단속 카메라 <D4>

for t in range(1, int(input()) + 1):
    N = int(input())
    K = int(input())
    camera = sorted(list(set(map(int, input().split()))))
    ans = 0

    if K >= len(camera):
        print(f'#{t} {ans}')
    else:
        diff = []
        for i in range(len(camera)-1):
            diff.append(camera[i+1] - camera[i])
        diff.sort()
        print(f'#{t} {sum(diff[:len(diff) - (K-1)])}')
