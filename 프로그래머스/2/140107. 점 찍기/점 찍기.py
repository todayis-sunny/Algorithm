import math
def solution(k, d):
    result = 0
    # x 기준으로 세기
    for x in range(0, d + 1, k):
        info = math.floor(math.sqrt(d * d - x * x))
        result += (info // k) + 1
    return result 