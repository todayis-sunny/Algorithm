def solution(n):
    result = []
    # 앞에 계속 집어넣기
    for num in str(n):
        result.insert(0, int(num))
    return result