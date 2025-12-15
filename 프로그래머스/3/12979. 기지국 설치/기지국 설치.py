import math

def solution(n, stations, w):
    result = 0
    wave = w * 2 + 1 # 파동
    start = 1
    for s in stations:
        if s - w - start > 0:
            result += math.ceil((s - w - start) / wave)
        start = s + w + 1 # start 지점 변경
        
    if n - start + 1 > 0:
        result += math.ceil((n - start + 1) / wave)
    return result

# 파동을 나누고 올림처리한다. -> 그만큼 기지국을 설치해야 함.