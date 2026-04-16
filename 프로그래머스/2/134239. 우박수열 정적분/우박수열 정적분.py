def solution(k, ranges):
    answer = []
    integralArea = [0.0]
    
    while k != 1:
        # 우박 수열
        newK = (k//2) if k % 2 == 0 else (k*3+1)
        # 정적분 넓이
        minY, maxY = min(k, newK), max(k, newK)
        integralArea.append(integralArea[-1] + (minY + (1/2) * (maxY - minY)))
        # 자연수 k 갱신
        k = newK
    
    N = len(integralArea) # 그래프 길이
    
    for y1, y2 in ranges:
        # 정적분이 유효한 구간
        if N + (y2-1) >= y1: answer.append(integralArea[y2-1] - integralArea[y1])
        # 시작점이 끝점보다 커서 유효하지 않은 구간
        else: answer.append(-1)
        
    return answer