def dfs(numbers, target, idx, values):
    global reuslt
    
    # 깊이가 끝가지 닿은 경우
    if idx == len(numbers):
        # target과 같은 경우 cnt에 1을 더함
        if values == target:
            reuslt += 1
        return    
    
    # 재귀함수로 표현
    dfs(numbers, target, idx + 1, values + numbers[idx])
    dfs(numbers, target, idx + 1, values - numbers[idx])
    
def solution(numbers, target):
    
    global reuslt
    reuslt = 0
    dfs(numbers, target, 0, 0)
    
    return reuslt