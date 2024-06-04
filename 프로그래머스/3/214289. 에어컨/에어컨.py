# temp : 실외온도.
# t1, t2 : 범위.
# a : 희망온도와 실내온도가 다를 때의 1분당 소비전력.
# b : 희망온도와 실내온도가 같을 때의 1분당 소비전력.
# onboard : 승객이 탑승중 유무.
def solution(temp, t1, t2, a, b, onboard):
    # dp 테이블 초기화. dp[t][temp] : t - 초, temp - 현재온도.
    dp = [[1e9] * 53 for _ in range(len(onboard))]
    # -10 음수를 제거하기 위한 10, 쓸모없는 공간을 앞뒤로 1씩 주기 위한 1 -> 11을 더해줌.
    temp += 11
    t1 += 11
    t2 += 11
    # 초기화 설정.
    dp[0][temp] = 0 # 에어컨을 키지 않았을 시.
    dp[0][temp-1] = a # 에어컨 가동 -> 온도 하강.
    dp[0][temp+1] = a # 에어컨 가동 -> 온도 상승.
    
    
    # 손님이 탑승하는 흐름도에 따라 반복. i는 현재 초를 의미.
    for i in range(1, len(onboard)):
        # 승객 탑승. -> 유효한 범위만 계산.
        if onboard[i]:
            left, right = t1, t2+1
        # 승객 미탑승. -> 모든 범위 계산.
        else:
            left, right = 1, 52
            
        for t in range(left, right):
            # 온도를 1 증가하는 방법.
            if temp > t-1: # 외부 온도가 이전온도보다 높을때 비용 x
                dp[i][t] = min(dp[i][t], dp[i-1][t-1])
            else: # 에어컨을 사용해야 할때 비용 a
                dp[i][t] = min(dp[i][t], dp[i-1][t-1] + a)
            
            # 온도를 유지하는 방법.
            if temp == t: # 외부 온도와 유지하려는 온도가 같다면 비용 x
                dp[i][t] = min(dp[i][t], dp[i-1][t])
            else: # 외부 온도와 유지하려는 온도가 다르면 비용 b
                dp[i][t] = min(dp[i][t], dp[i-1][t] + b)

            # 온도를 1 감소하는 방법.
            if temp < t+1: # 외부 온도가 이전온도보다 낮을때 비용 x
                dp[i][t] = min(dp[i][t], dp[i-1][t+1])
            else: # 에어컨을 사용해야 할때 비용 a
                dp[i][t] = min(dp[i][t], dp[i-1][t+1] + a)
    
    # 마지막에 승객이 없다면
    if not onboard[-1]:
        return min(dp[-1])
    # 마지막에 승객이 있다면
    else:
        return min(dp[-1][t1:t2+1])
    