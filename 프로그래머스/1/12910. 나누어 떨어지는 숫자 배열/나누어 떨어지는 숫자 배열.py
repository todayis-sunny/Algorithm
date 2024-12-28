def solution(arr, divisor):
    answer = []
    
    for element in arr:
        if element % divisor == 0:
            answer.append(element)
    if answer:
        return sorted(answer)
    
    return [-1]