def solution(coin, cards):
    length = len(cards)
    goal = length + 1
    freeIdx = length //3 - 1
    
    idxArr = [0] * (length + 1)
    for i in range(length):
        idxArr[cards[i]] = i
        
    combi = [0, 0, 0]
    # 전처리
    for j in range(freeIdx + 1):
        if idxArr[goal - cards[j]] <= j:
            combi[0] += 1
    
    
    # 라운드
    answer = 0
    # 현재까지의 인덱스
    curr = freeIdx
    # 남은 코인
    coins = coin
    # 인덱스가 마지막까지 도달할때 까지 반복
    while True:
        answer += 1
        if curr == length-1:
            break
        card1 = cards[curr+1]
        card2 = cards[curr+2]
        
        curr += 2
        # card1에 대한 처리.
        if freeIdx < idxArr[goal - card1] < idxArr[card1]:
            combi[2] += 1
        elif idxArr[goal - card1] <= freeIdx:
            combi[1] += 1
        # card2에 대한 처리. 
        if freeIdx < idxArr[goal - card2] < idxArr[card2]:
            combi[2] += 1
        elif idxArr[goal - card2] <= freeIdx:
            combi[1] += 1
        
        # 뺄게 있으면 계속 진행
        for i in range(3):
            if combi[i]:
                coins -= i
                combi[i] -= 1
                break
        # 뺄게 없으면 break    
        else:
            break
        # coin이 부족해지면 break
        if coins < 0:
            break
    
    return answer