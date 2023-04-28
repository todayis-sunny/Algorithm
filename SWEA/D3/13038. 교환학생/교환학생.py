# 13038. 교환학생 <D3>

tc = int(input())

for t in range(1, tc + 1):
    # 수업을 들어야 하는 총 횟수
    n = int(input())
    days = list(map(str, input().split()))
    # 한 주에 수업하는 횟수
    times = days.count('1')

    # 최적의 수업 시작일 찾기
    tmp = ''.join(days) * 2
    arr = []
    for i in range(7):
        arr.append(tmp[i:i+7])
    arr = sorted(arr, reverse=True)
    lec = arr[0]

    answer = []
    div, mod = divmod(n, times)
    for a in arr:
        cnt = 0
        tmp = 0

        if div > 0 and mod == 0:
            for l in a:
                cnt += 1
                tmp += int(l)
                if tmp == times:
                    break
            ans = (div-1) * 7 + cnt

        else:
            for l in a:
                cnt += 1
                tmp += int(l)
                if tmp == mod:
                    break
            ans = div * 7 + cnt


        answer.append(ans)
    print(f'#{t} {min(answer)}')
