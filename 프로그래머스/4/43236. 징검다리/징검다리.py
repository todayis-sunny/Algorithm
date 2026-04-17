def solution(distance, rocks, n):
    rocks.append(distance)
    rocks.sort()
    left, right = 1, distance

    while left <= right:
        prev = 0 # 이전 지점
        cnt = 0 # 제거한 바위 수
        mid = (left + right) // 2
        for curr in rocks:
            if curr - prev < mid: # 거리의 최솟값이 더 작아지려고 하는 경우 바위를 제거
                 cnt += 1
            else: # 그 반대인 경우는 prev 갱신
                prev = curr
        if cnt > n: # 조건보다 더 많이 제거한 경우
            right = mid - 1
        else: # 조건에 충족한 경우
            left = mid + 1
    return right
