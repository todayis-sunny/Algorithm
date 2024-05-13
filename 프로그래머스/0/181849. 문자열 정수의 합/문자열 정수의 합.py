def solution(num_str):
    answer = 0
    for i in range(len(num_str)):
        answer += int(num_str[i])
    return answer