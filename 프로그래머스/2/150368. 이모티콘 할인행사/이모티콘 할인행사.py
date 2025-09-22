# 할인율
discount = [10, 20, 30 ,40]

    
def solution(users, emoticons): 
    # 정답
    ans = [0, 0]
    # 이모티콘 최대 개수
    limit = len(emoticons)
    # 이모티콘 할인율
    emoDiscount = [0] * limit 
    
    # 해당 할인율로 탐색
    def run():
        totalPlus = 0
        totalCost = 0
        # 유저 한명씩 탐색
        for user in users:
            cost = 0
            for i in range(limit):
                # 이모티콘 할인이 유저의 희망 할인율보다 높은 경우
                if emoDiscount[i] >= user[0]:
                    # 이모티콘 구매
                    cost += emoticons[i] * (100 - emoDiscount[i]) / 100
                # 구매한 이모티콘들이 유저의 희망 한도이상인 경우
                if cost >= user[1]:
                    # 이모티콘 플러스 가입
                    totalPlus += 1
                    break
            # 해당 유저가 플러스 가입을 하지 않은 경우 산 이모티콘들을 최종 합계에 기입
            else:
                totalCost += cost
        # 플러스 가입유저가 더 많아지는 경우 갱신
        if totalPlus > ans[0]:
            ans[0] = totalPlus
            ans[1] = totalCost
        # 플러스 가입유저가 같은 경우 비용 갱신
        elif totalPlus == ans[0]:
            ans[1] = max(ans[1], totalCost)
        return
            
    def dfs(idx):
        # 최대 개수인 경우 종료
        if idx == limit:
            run()
            return
        # 이모티콘 할인율 4가지 적용(10%, 20%, 30%, 40%)
        for i in range(4):
            emoDiscount[idx] = discount[i]
            dfs(idx + 1)
            
    dfs(0)
    return ans




# 목표
    # 서비스 가입자를 최대한 늘리는 것 (우선)
    # 판매액을 최대한 늘리는 것
# 방법
    # 완탐
    # 경우의 수 : 7 * 4 * 100