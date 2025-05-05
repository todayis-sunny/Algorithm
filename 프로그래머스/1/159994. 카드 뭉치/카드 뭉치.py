from collections import deque

def solution(cards1, cards2, goal):
    ans = "Yes"
    
    cards1 = deque(cards1)
    cards2 = deque(cards2)
    for i in goal:
        if (len(cards1) != 0) and (i == cards1[0]):
            cards1.popleft()
        elif (len(cards2) != 0) and (i == cards2[0]):
            cards2.popleft()
        else:
            ans = "No"
            break
        
    return ans