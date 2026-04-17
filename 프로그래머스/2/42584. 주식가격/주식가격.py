def solution(prices):
    length = len(prices)
    
    # answer을 max값으로 초기화  
    ans = [ i for i in range (length-1, -1, -1)]
    
    # 주식 가격이 떨어질 경우 찾기
    stack = [0]
    for i in range (1, length):
        while stack and prices[stack[-1]] > prices[i]:
            j = stack.pop()
            ans[j] = i - j
        stack.append(i)
    return ans