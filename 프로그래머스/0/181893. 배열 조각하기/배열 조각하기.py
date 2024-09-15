def solution(arr, query):
    for idx, query in enumerate(query):
        if idx % 2 == 1:
            arr = arr[query:]
        else:
            arr = arr[:query+1]
    return arr