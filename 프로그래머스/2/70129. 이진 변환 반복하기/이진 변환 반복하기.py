def solution(x):
    result = []
    
    count = 0
    zero = 0
    
    while True:
        if x == '1':
            break
        zero = zero + x.count("0")
        x = x.replace("0", "")
        
        x = bin(len(x))[2:]
        
        count = count + 1
    
    result = [count, zero]
    
    return result