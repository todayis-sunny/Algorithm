def solution(cookie):
    # 과자 수
    result = 0
    # 분기점을 만들어내는 지점
    for i in range(len(cookie) - 1):
        leftValue, leftIdx = cookie[i], i
        rightValue, rightIdx = cookie[i + 1], i + 1
        while True:
            # 좌측 합 == 우측 합, 이전의 max 값보다 큰 경우 값 갱신
            if leftValue == rightValue and leftValue > result:
                result = leftValue
            # 좌측을 늘려 봄
            if leftIdx > 0 and leftValue <= rightValue:
                leftIdx -= 1
                leftValue += cookie[leftIdx]
            # 우측을 늘려 봄
            elif rightIdx < len(cookie) - 1 and rightValue <= leftValue:
                rightIdx += 1
                rightValue += cookie[rightIdx]
            else:
                break
                
    return result