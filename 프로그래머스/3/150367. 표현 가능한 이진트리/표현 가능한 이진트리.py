def solution(numbers):
    result = []
    # 순회하기
    for num in numbers:
        number = format(num, 'b')
        length = len(number)
        # 길이를 조정하기 : 포화이진트리 2**n - 1
        for i in range(1, 50, 1):
            l = (2 ** i - 1)
            if length <= l:
                number = '0' * (l - length) + number
                break
        # dfs 검사
        mid = number[len(number) // 2]
        result.append(dfs(mid, number))
    return result

def dfs(mid, number):
    # 2진수의 길이
    length = len(number)
    if length == 1:
        return 1
    # 2진수의 중간값
    m = len(number) // 2
    if mid == '0':
        # 가운데 값이 0이면 둘다 0이어야 함
        if int(number[:m]) == 0 and int(number[m + 1:]) == 0:
            return 1
        return 0
    else:
        # 좌측 숫자 및 중간값
        lNum = number[:m]
        lMid = lNum[len(lNum) // 2]
        # 우측 숫자 및 중간값
        rNum = number[m + 1:]
        rMid = rNum[len(rNum) // 2]
        return dfs(lMid, lNum) * dfs(rMid, rNum)
        