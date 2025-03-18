def solution(diffs, times, limit):
    maxLevel = max(diffs)  #최대 diff 구해두기
    l = 1
    r = maxLevel
    answer = maxLevel  #정답이 max_diff인 경우를 대비해서 answer에 넣어두기

    while l < r:
        level = int((l + r) /2)
        time = times[0]
        
        for i in range(1, len(diffs)):
            k = 0
            if level < diffs[i]:
                k = diffs[i] - level
            time += (times[i] + times[i -1]) * k + times[i]

        if time <= limit:
            r = level
            answer = level  
            #구간이 좁아질때마다 항상 문제를 시간안에 풀 수 있는 r을 정답으로 설정
        else:
            l = level +1    
            #현재 level은 정답이 아니므로 범위 자체에서 제외

    return answer