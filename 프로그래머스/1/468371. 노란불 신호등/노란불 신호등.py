from math import gcd

def solution(signals):
    # 신호정보
    info = []
    # 신호정보 기입
    for sig in signals:
        info.append(Signal(sig))
    # 최대 1주기 계산
    limit = 1
    for x in info:
        limit = lcm(limit, x.cycle)
    # 1주기에 대한 노란 신호 체크 여부
    for t in range(limit):
        for x in info:
            # 신호 x에 대한 (1주기)단위 시간
            time = t % x.cycle
            # 노란불에 걸려 있지 않은 경우
            if time < x.start or time > x.end:
                break
        # 모든 신호가 노란불인 경우
        else:
            return (t + 1) # 0초 시작 -> 1초 시작
    return -1
            
def lcm(a, b):
    return (a * b) // gcd(a, b)
    
class Signal:
    def __init__(self, sig):
        self.cycle = sum(sig) # 주기
        self.start = sig[0] # 노란불 시작시간
        self.end = sig[0] + sig[1] - 1 # 노란불 종료시간


        
# 주기가 반복될 것이므로 1주기에 대한 정보로만 문제를 풀이한다.
# 최적화 버전 lcm으로 1주기를 표현

