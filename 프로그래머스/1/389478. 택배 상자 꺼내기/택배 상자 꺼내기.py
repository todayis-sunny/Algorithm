def solution(n, w, num):
    boxs = [[] for _ in range(w)]
    box_num = 0
    direction = 1  # 1이면 오른쪽, -1이면 왼쪽

    while box_num < n:
        for idx in range(0, w, direction) if direction == 1 else range(w - 1, -1, -1):
            box_num += 1
            boxs[idx].append(box_num)
            if box_num == n:
                break
        direction *= -1  # 방향 전환

    for values in boxs:
        if num in values:
            return len(values) - values.index(num)
    
    return 0