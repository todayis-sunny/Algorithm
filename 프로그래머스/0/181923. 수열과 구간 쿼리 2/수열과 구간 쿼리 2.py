def solution(arr, queries):
    result = []
    for query in queries:
        temp_list = []
        for i in range(query[0], query[1] + 1):
            if arr[i] > query[2]:
                temp_list.append(arr[i])
        try:   
            result.append(min(temp_list))
        except:
            result.append(-1)
    return result