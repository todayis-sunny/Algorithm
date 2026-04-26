def solution(dist_limit, split_limit):
    max_leaf = 1
    
    i = 0
    power2 = 1
    # 1. 가능한 2분배 층수(i)를 모두 탐색 (2^i <= split_limit)
    while power2 <= split_limit:
        
        # 2. 고정된 i에 대해, 허용되는 최대 3분배 층수(j)를 계산
        # (명제에 따라 j는 무조건 최대치로 꽉 채우는 것이 이득임)
        max_3_pow = split_limit // power2
        j = 0
        power3 = 1
        while power3 * 3 <= max_3_pow:
            j += 1
            power3 *= 3
            
        # 3. 결정된 (i, j) 층수를 바탕으로 예산 차감 시뮬레이션
        budget = dist_limit  # 남은 예산(분배 가능 횟수)
        frontier = 1         # 현재 층의 노드 수 (루트 1개로 시작)
        current_leaf = 1     # 현재까지의 리프 노드 수
        
        # [2분할 구간 시뮬레이션]
        for _ in range(i):
            if budget == 0: break
            
            # 현재 층의 노드 전부(frontier)와 남은 예산(budget) 중 작은 만큼만 분배 가능
            splits = min(budget, frontier)
            budget -= splits
            
            # 2분할을 1회 할 때마다 노드가 2개로 갈라지므로 리프는 1개(2-1) 증가
            current_leaf += splits * 1
            
            # 분배된 노드들은 다음 층의 프런티어가 됨
            # (분배 안 된 노드는 리프로 남고 프런티어에서 제외됨)
            frontier = splits * 2 
            
        # [3분할 구간 시뮬레이션]
        if budget > 0:
            for _ in range(j):
                if budget == 0: break
                
                splits = min(budget, frontier)
                budget -= splits
                
                # 3분할을 1회 할 때마다 노드가 3개로 갈라지므로 리프는 2개(3-1) 증가
                current_leaf += splits * 2
                
                frontier = splits * 3
                
        # 4. 최대 리프 수 갱신
        max_leaf = max(max_leaf, current_leaf)
        
        # 다음 i 탐색을 위해 2를 곱해줌
        i += 1
        power2 *= 2
        
    return max_leaf