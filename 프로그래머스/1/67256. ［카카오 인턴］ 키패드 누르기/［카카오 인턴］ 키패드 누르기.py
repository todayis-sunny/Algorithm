def solution(numbers, hand):
    keypad = [(3,1), (0,0), (0,1), (0,2), (1,0), (1,1), (1,2), (2,0), (2,1), (2,2), (3,0), (3, 2)]
    L, R = 10, 11
    answer = ''
    
    for num in numbers:
        if num in [1, 4, 7]:
            L = num
            answer += 'L'
        elif num in [3, 6, 9]:
            R = num
            answer += 'R'
        else:
            midRow = keypad[num][0]
            midCol = keypad[num][1]
            
            LRow = keypad[L][0]
            LCol = keypad[L][1]
            
            RRow = keypad[R][0]
            RCol = keypad[R][1]
            
            Ldist = abs(midRow - LRow) + abs (midCol - LCol)
            Rdist = abs(midRow - RRow) + abs(midCol - RCol)
            if Ldist < Rdist:
                L = num
                answer += 'L'
            elif Ldist > Rdist:
                R = num
                answer += 'R'
            else:
                if hand == "left":
                    L = num
                    answer += 'L'
                else:
                    R = num
                    answer += 'R'
                    
    
    return answer