def solution(numbers):
    answer = ""
    # string으로 바꾸어서 비교
    numbers = list(map(str, numbers))
    # 3번씩 반복하면 붙였을때 큰수 찾기 가능
    numbers.sort(key = lambda x : x * 3,reverse = True)  
    
    # 정렬된 리스트를 answer에 순서대로 더해줌
    for n in numbers: 
        answer += n
    
    return str(int(answer))