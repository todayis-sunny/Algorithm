INF = 1e9
def solution(temp, t1, t2, a, b, onboard):
    alpha = 11
    A = a
    B = b
    t1 += alpha
    t2 += alpha
    temp += alpha
    # 승객의 탑승여부 (길이 = 최대시간)  
    onboard = [0] + onboard
    length = len(onboard)
    # dp초기화 : 무한대값 넣어서 처리
    dp = [[INF] * 53 for _ in range(length)]
    # 초기 실내온도는 비용이 0
    dp[0][temp] = 0
    
    # 시간순으로 가겠구나
    # [과거(i - 1) -> 현재(i)]
    for t in range(1, length):
        # 손님여부 : 탐색의 범위 달라짐
        left, right = 1, 51
        # 손님이 탐
        if onboard[t]:
            left, right = t1, t2
        # 손님이 안탐
        else:
            left, right = 1, 51
        # 점화식 : 이전의 시간에서 값을 끌고 와서 사용함
        for c in range(left, right + 1):
            # 온도를 상승하는 방법 (에어컨을 킨경우, 끈경우) c - 1 -> c
            # 에어컨을 끔 : 실내온도 -> 실외온도 수렴 (실내온도 < 실외온도)
            # 에어컨을 킴 : 실내온도 -> 실외온도 방향 (a : 실내온도 != 실외온도, b : 실내온도 = 실외온도)
            if c - 1 < temp: # (실내온도 < 실외온도) , 에어컨을 사용하지 않는 경우
                dp[t][c] = min(dp[t][c], dp[t - 1][c - 1])
            else: # 에어컨을 사용하는 경우 (실내온도 >= 실외온도) 희망온도 c 실내온도 c-1
                dp[t][c] = min(dp[t][c], dp[t - 1][c - 1] + A)
            # 온도를 유지하는 방법 (에어컨 x, o)
            # 에어컨을 끔 : 실내온도 -> 실외온도 수렴 (실내온도 = 실외온도)
            # 에어컨을 킴 : 실내온도 -> 실외온도 방향 (a : 실내온도 != 실외온도, b : 실내온도 = 실외온도)
            if c == temp: # (실내 == 실외)
                dp[t][c] = min(dp[t][c], dp[t - 1][c]) # off 비용 x
            else: # On 희망온도 c 실내온도 c
                dp[t][c] = min(dp[t][c], dp[t - 1][c] + B) # 
                
            # 온도를 하강하는 방법
            # 에어컨을 끔 : 실내온도 -> 실외온도 수렴 (실내온도 > 실외온도)
            # 에어컨을 킴 : 실내온도 -> 실외온도 방향 (a : 실내온도 != 실외온도, b : 실내온도 = 실외온도)
            if c + 1 > temp: # (실내 > 실외)
                dp[t][c] = min(dp[t][c], dp[t - 1][c + 1])
            else: # (실내 <= 실외)
                dp[t][c] = min(dp[t][c], dp[t - 1][c + 1] + A)
    
        
    return min(dp[-1])




# dp[t][c] = k : 시간 t에서 온도 c를 만들때 최소 비용 k
# 범위 t = len(onb) c = 0 ~ 52 | 의미있는 값[1 ~ 51] 쓰레기 값(0, 52) 사이즈 53 

# 명제
# 실내온도는 실외온도와 같습니다. 초기에는 외부온도를 가지고 초기화

# a,b의 관계
# 에어컨의 희망온도와 실내온도가 다르다면 매 분 전력을 a만큼 소비
# 희망온도와 실내온도가 같다면 매 분 전력을 b만큼 소비

# 에어컨을 끄면
# 에어컨의 전원을 끄면 실내온도가 실외온도와 같아지는 방향으로 매 분 1도 상승 또는 하강
# 에어컨이 꺼져 있다면 전력을 0만큼 소비

# 출력
# 최소 소비전력 = dp[length - 1][x]
# 100점입니다
# 쓰레기값


# 설계 단계

# onboard + 1

# 케이스
# 온도를 1 상승
# 온도를 유지
# 온도를 1 하강

# 손님여부
# 손님 o -> a ~ b
# 손님 x -> 0 ~ 50

# 로직
# t시간순으로 온도에 대한 비용를 갱신


















###




# temp : 실외온도.
# t1, t2 : 범위.
# a : 희망온도와 실내온도가 다를 때의 1분당 소비전력.
# b : 희망온도와 실내온도가 같을 때의 1분당 소비전력.
# onboard : 승객이 탑승중 유무.
# def solution(temp, t1, t2, a, b, onboard):
#     # dp 테이블 초기화. dp[t][temp] : t - 초, temp - 현재온도.
#     dp = [[1e9] * 53 for _ in range(len(onboard))]
#     # -10 음수를 제거하기 위한 10, 쓸모없는 공간을 앞뒤로 1씩 주기 위한 1 -> 11을 더해줌.
#     temp += 11
#     t1 += 11
#     t2 += 11
#     # 초기화 설정.
#     dp[0][temp] = 0 # 에어컨을 키지 않았을 시.
#     dp[0][temp-1] = a # 에어컨 가동 -> 온도 하강.
#     dp[0][temp+1] = a # 에어컨 가동 -> 온도 상승.
    
    
#     # 손님이 탑승하는 흐름도에 따라 반복. i는 현재 초를 의미.
#     for i in range(1, len(onboard)):
#         # 승객 탑승. -> 유효한 범위만 계산.
#         if onboard[i]:
#             left, right = t1, t2+1
#         # 승객 미탑승. -> 모든 범위 계산.
#         else:
#             left, right = 1, 52
            
#         for t in range(left, right):
#             # 온도를 1 증가하는 방법.
#             if temp > t-1: # 외부 온도가 이전온도보다 높을때 비용 x
#                 dp[i][t] = min(dp[i][t], dp[i-1][t-1])
#             else: # 에어컨을 사용해야 할때 비용 a
#                 dp[i][t] = min(dp[i][t], dp[i-1][t-1] + a)
            
#             # 온도를 유지하는 방법.
#             if temp == t: # 외부 온도와 유지하려는 온도가 같다면 비용 x
#                 dp[i][t] = min(dp[i][t], dp[i-1][t])
#             else: # 외부 온도와 유지하려는 온도가 다르면 비용 b
#                 dp[i][t] = min(dp[i][t], dp[i-1][t] + b)

#             # 온도를 1 감소하는 방법.
#             if temp < t+1: # 외부 온도가 이전온도보다 낮을때 비용 x
#                 dp[i][t] = min(dp[i][t], dp[i-1][t+1])
#             else: # 에어컨을 사용해야 할때 비용 a
#                 dp[i][t] = min(dp[i][t], dp[i-1][t+1] + a)
    
#     # 마지막에 승객이 없다면
#     if not onboard[-1]:
#         return min(dp[-1])
#     # 마지막에 승객이 있다면
#     else:
#         return min(dp[-1][t1:t2+1])
    