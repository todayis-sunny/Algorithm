def solution(routes):
    answer = 0
    routes.sort(key = lambda x  : x[1]) # routes를 차량이 나간 지점 (진출) 기준으로 정렬
    camera = -30_001 # -30_001부터 카메라 위치를 찾기

    for route in routes:
        if camera < route[0]:
            answer += 1
            camera = route[1]
    return answer