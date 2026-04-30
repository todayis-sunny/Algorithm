MOD = 10_007

def solution(n, tops):
    dpN = [0] * n
    dpS = [0] * n
    # 0번째
    dpN[0] = 2 + tops[0]
    dpS[0] = 1
    
    # 반복
    for i in range(1, n):
        dpN[i] = dpN[i - 1] * (2 + tops[i]) + dpS[i - 1] * (1 + tops[i])
        dpS[i] = dpN[i - 1] + dpS[i - 1]
        
        dpN[i] %= MOD
        dpS[i] %= MOD
        
    return (dpN[-1] + dpS[-1]) % MOD


# 요점

# 1. 지붕이 있거나 없거나
# 2-1. 지붕이 없으면 4번 도형은 쓸수가 없음
# 2-2. 지붕이 있던 없던 1,2,3번 도형을 쓸수 있다.

# 3. 2,3,4도형으로 타일링을 하고 남은 공간은 1번 도형으로 타일링

# 4. 하나의 케이스에 마름모는 2,3,4 도형 하나밖에 쓰지를 못한다.