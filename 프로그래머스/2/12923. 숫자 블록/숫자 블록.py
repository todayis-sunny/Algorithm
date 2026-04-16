from math import sqrt
def solution(begin, end):
    answer = []
    
    for i in range(begin,end+1):
        if i==1:
            answer.append(0)
        else:
            temp=[1]
            for j in range(2, int(sqrt(i))+1):
                if j<=10**7 and i%j==0:
                    temp.append(j)
                    if i!=i//j<=10**7:
                        temp.append(i//j)
                        
            answer.append(max(temp))

    return answer