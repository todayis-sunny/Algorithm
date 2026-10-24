def solution(n, w, num):
    boxs = [[] for _ in range(w)]
    count = 0
    direction = 1  # 1이면 오른쪽, -1이면 왼쪽

    while count < n:
        for idx in range(0, w, direction) if direction == 1 else range(w - 1, -1, -1):
            count += 1
            boxs[idx].append(count)
            if count == n:
                break
        direction *= -1  # 방향 전환

    for values in boxs:
        if num in values:
            return len(values) - values.index(num)
    
    return 0