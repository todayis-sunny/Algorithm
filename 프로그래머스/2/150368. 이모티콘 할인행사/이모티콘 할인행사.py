from itertools import product

# 할인율
discount = [10, 20, 30 ,40]

    
def solution(users, emoticons): 
    # 정답
    ans = [0, 0]
    # 이모티콘 최대 개수
    limit = len(emoticons)
    # 이모티콘 할인율
    emoDiscount = [0] * limit 
    
    prod = list(product(discount, repeat = limit)) 
    # 할인율 리스트로 탐색
    for p in prod:
        # uD: 유저의 희망 할인율, uP: 유저의 희망 한도
        totalPlus = 0
        totalCost = 0
        for uD, uP in users:
            cost = 0
            # eC: 이모티콘 가격, eD: 이모티콘 할인율
            for eC, eD in zip(emoticons, p):
                # 이모티콘 할인이 유저의 희망 할인율보다 높은 경우
                if eD >= uD:
                    # 이모티콘 구매
                    cost += eC * (100 - eD) / 100
                # 구매한 이모티콘들이 유저의 희망 한도이상인 경우
                if cost >= uP:
                    # 이모티콘 플러스 가입
                    totalPlus += 1
                    break
            # 플러스를 가입하지 않고 이모티콘을 구입한 경우 구입금액 더하기
            else:
                totalCost += cost
        # 플러스 가입유저가 더 많아지는 경우 갱신
        if totalPlus > ans[0]:
            ans[0] = totalPlus
            ans[1] = totalCost
        # 플러스 가입유저가 같은 경우 비용 갱신
        elif totalPlus == ans[0]:
            ans[1] = max(ans[1], totalCost)
                
    return ans


# 목표
    # 서비스 가입자를 최대한 늘리는 것 (우선)
    # 판매액을 최대한 늘리는 것
# 방법
    # 완탐
    # 경우의 수 : 7 * 4 * 100