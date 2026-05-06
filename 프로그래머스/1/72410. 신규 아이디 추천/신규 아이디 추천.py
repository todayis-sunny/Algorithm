def solution(new_id):
    result = new_id
    # 1단계: 대문자 -> 소문자
    result = result.lower()
    # 2단계: 특수문자 제거
    result = "".join([ch for ch in result if (ch.islower() or ch.isdigit() or ch in '-_.')])
    # 3단계~4단계: 마침표 한개로 (양 끝은 제거)
    temp = ""
    buffer = ""
    flag = '.'
    for ch in result:
        if ch == '.':
            if ch != flag:
                buffer += ch
        else:
            buffer += ch
            temp += buffer
            buffer = ""
        flag = ch
    result = temp
    # 5단계: 빈 문자열이면 "a"
    if not result:
        result = "a"
    # 6단계: 16자 이상이면 자르기
    result = result[:min(15, len(result))]
    result = result.strip('.') # 양쪽 .지우기
    # 7단계: 길이 2이하 -> 마지막 문자 길이 3될때까지 붙히기
    result += result[-1] * max(0, 3 - len(result))
    
        
    return result