def solution(n,a,b):
    # 발생할 수 있는 최대 라운드 수 계산
    max_round = 0
    while 2 ** max_round < n:
        max_round += 1
    
    # 이진 탐색 적용
    a, b = min(a, b), max(a, b)  # 큰 수, 작은 수 구분
    mid = n // 2  # 최초 중간값
    lower = 0  # 최초 최소값
    upper = n  # 최초 최대값
    
    while True:
        # 탈출 조건 // 두 수가 포함된 라운드가 서로 다를 경우
        if a <= mid and b > mid:
            break
        # 작은 수가 중간값보다 클 경우(라운드에서 두 수가 같은 그룹에 있을 경우)
        elif a >= mid:
            lower = mid
            mid = (mid + upper) // 2
            max_round -= 1  # 라운드값 -1
        # 큰 수가 중간값보다 작거나 같을 경우(라운드에서 두 수가 같은 그룹에 있을 경우)
        elif b <= mid:
            upper = mid
            mid = (mid + lower) // 2
            max_round -= 1  # 라운드값 -1
            
    return max_round