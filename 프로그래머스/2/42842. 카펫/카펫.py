def solution(brown, yellow):
    for y in range(1, yellow+1):
    
        x = yellow // y
        
        if yellow % y:
            continue
            
        if (2*x + 2*y + 4) == brown:
            return [x+2, y+2]