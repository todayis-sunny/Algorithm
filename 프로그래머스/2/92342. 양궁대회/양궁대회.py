def solution(n, info):
    result = [0] * 11
    tmp = [0] * 11
    maxDiff = 0
    
    for subset in range(1, 1 << 10):
        ryan = 0
        apeach = 0
        cnt = 0
        
        for i in range(10):
            if subset & (1 << i):
                ryan += 10 - i
                tmp[i] = info[i] + 1
                cnt += tmp[i]
            else:
                tmp[i] = 0
                if info[i]:
                    apeach += 10 - i
        if cnt > n:
            continue
        tmp[10] = n - cnt
        
        if ryan - apeach == maxDiff:
            for i in reversed(range(11)):
                if tmp[i] > result[i]:
                    maxDiff = ryan - apeach
                    result = tmp[:]
                    break
                elif tmp[i] < result[i]:
                    break
                    
        elif ryan - apeach > maxDiff:
            maxDiff = ryan - apeach
            result = tmp[:]
    
    if maxDiff == 0:
        result = [-1]
        
    return result