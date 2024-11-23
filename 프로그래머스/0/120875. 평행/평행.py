def solution(dots):
    incline = []
    
    for i in range(3):
        for j in range(i+1, 4):
            incline.append((dots[i][1]-dots[j][1]) / (dots[i][0]-dots[j][0]))
            
    print(incline)
    
    for i in range(3):
        if incline[i] == incline[-i-1]:
            return 1
        
    return 0