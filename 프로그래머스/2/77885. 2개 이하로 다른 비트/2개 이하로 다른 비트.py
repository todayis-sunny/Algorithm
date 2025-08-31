def solution(numbers):
    answer = []

    for n in numbers:
        if n % 2 == 0:  # 짝수일 경우
            answer.append(n + 1)
        else:  # 홀수 인 경우
            temp = '0' + bin(n)[2:]
            rIdx = temp.rfind('0')
            tempList = list(temp)
            
            tempList[rIdx] = '1'
            tempList[rIdx + 1] = '0'
            
            tempStr = "".join(tempList)
            
            answer.append(int(tempStr, 2))
                
    return answer