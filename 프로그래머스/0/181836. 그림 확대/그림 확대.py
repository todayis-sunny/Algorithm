def solution(picture, k):
    answer = []
    
    for row in picture:
        resized = ''
        
        for pixel in row:
            resized += pixel * k
        
        for _ in range(k):
            answer.append(resized) 
    
    return answer