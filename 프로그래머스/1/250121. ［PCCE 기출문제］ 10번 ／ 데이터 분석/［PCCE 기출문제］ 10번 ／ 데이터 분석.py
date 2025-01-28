def solution(data, ext, val_ext, sort_by):
    answer = []
    # 컬럼 정보
    col_info = {
                "code" : 0,
                "date" : 1,
                "maximum" : 2,
                "remain" : 3
                }
    
    # ext 값이 val_ext보다 작은 데이터만 추출 및 적재
    for i in data:
        if i[col_info[ext]] < val_ext:
            answer.append(i)
                     
    # sort_by 컬럼 기준으로 오름차순 정렬
    answer.sort(key = lambda x : x[col_info[sort_by]])
    return answer