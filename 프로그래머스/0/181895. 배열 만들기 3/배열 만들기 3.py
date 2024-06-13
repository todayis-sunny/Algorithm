def solution(arr, intervals):
    answer = []
    for it in intervals:
        answer.extend(arr[it[0]:it[1]+1])
    return answer