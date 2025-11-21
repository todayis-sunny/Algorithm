import math
def solution(k, d):
    answer = 0
    # x 기준으로 세기
    for x in range(0, d + 1, k):
        result = math.floor(math.sqrt(d * d - x * x))
        answer += (result // k) + 1
    return answer 