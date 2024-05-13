def solution(num_list):
    if len(num_list) >= 11:
        return sum(num_list)
    answer = 1
    for num in num_list:
        answer *= num
    return answer