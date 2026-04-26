from math import sqrt
def solution(begin, end):
    result = []
    
    for i in range(begin, end + 1):
        if i==1:
            result.append(0)
        else:
            temp=[1]
            for j in range(2, int(sqrt(i)) + 1):
                if j <= (10 ** 7) and i % j==0:
                    temp.append(j)
                    if i != (i // j) <=10 ** 7:
                        temp.append(i // j)
                        
            result.append(max(temp))

    return result