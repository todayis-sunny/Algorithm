import math

def solution(r1, r2):
    ans = 0
    
    for x in range(1, r2 + 1):
        y_max = math.floor(math.sqrt(r2 ** 2 - x ** 2))
        y_min = 0 if x >= r1 else math.ceil(math.sqrt(abs(r1 ** 2 - x ** 2)))
        ans += y_max - y_min + 1
    return ans * 4