from collections import deque

def solution(players, m, k):
    ans = 0
    curr = 0 # 운영중인 서버 수
    dq = deque() 
    # 인원 체크
    for t in range(24):
        # 서버 점검
        if dq and dq[0][0] == t: # 종료해야하는 시간이면 서버 수 감소
            curr -= dq[0][1]
            dq.popleft()

        player = players[t] # 게임 이용자 수
        need = player // m
        # 서버를 증설해야하는 경우
        if need > curr:
            plus = need - curr
            curr += plus
            ans += plus
            dq.append((t + k, plus))
        
    return ans