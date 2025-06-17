def solution(cards):
    n = len(cards)
    v = [0 for _ in range(n + 1)]
    
    stack = []
    
    def dfs(s, cnt):
        nextC = cards[s - 1]
        if v[nextC]:
            stack.append(cnt)
            return
        v[nextC] = 1
        dfs(nextC, cnt + 1)
    
    for card in cards:
        if v[card]:
            continue
        v[card] = 1
        dfs(card, 1)
    
    answer = 0
    if len(stack) >= 2:
        stack.sort()
        answer = stack[-1] * stack[-2]
        
    return answer