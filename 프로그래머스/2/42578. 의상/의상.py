def solution(clothes):
    # 각 종류별 가진 의상을 저장 (종류:[이름, 이름, ...])
    closet = {} 
    for name, kind in clothes:
        if kind in closet.keys():
            closet[kind] += [name]
        else:
            closet[kind] = [name]
    
    # A의 종류가 N개, B의 종류가 M개 일 때 가능한 모든 경우의 수 (N+1)(M+1)
    answer = 1
    for _, value in closet.items():
        answer *= (len(value) + 1)
        
    return answer -1