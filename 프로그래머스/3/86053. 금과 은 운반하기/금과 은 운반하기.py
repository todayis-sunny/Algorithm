def solution(a, b, g, s, w, t):
    left = 0
    right = (10 ** 9) * (10 ** 5) * 4
    result = right
    # 시간을 기준으로 이분탐색
    while left <= right:
        mid = (left + right) // 2
        # 지금까지 옮긴 금, 은, (금 + 은)
        gSum, sSum, total = 0, 0, 0
        # 각 도시별 순회
        for gold, silver, weight, time in zip(g, s, w, t):
            # 주어진 시간 내에서 아동할 수 있는 총 횟수 (왕복)
            cnt = mid // (time * 2)
            # 시간이 남으면 편도로 한번 더 이동
            if mid % (time * 2) >= time:
                cnt += 1
            # 주어진 시간 동안 운반할 수 있는 총량
            possible = cnt * weight
            # 가능한 만큼 운반
            gSum += min(gold, possible)
            sSum += min(silver, possible)
            # (금 + 은) 옮긴 총량은 possible을 넘길수 없음
            total += min(gold + silver, possible)

        # 제한시간 내에 다 옮긴 경우
        if total >= a + b and gSum >= a and sSum >= b:
            right = mid - 1
            result = min(mid, result)
        # 시간이 부족한 경우
        else:
            left = mid + 1

    return result