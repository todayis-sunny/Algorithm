def solution(n):
    answer = []
    # 앞에 계속 집어넣기
    for num in str(n):
        answer.insert(0, int(num))
    return answer