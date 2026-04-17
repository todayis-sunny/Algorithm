import re

def solution(message, spoiler_ranges):
    # 1. 스포일러가 묻은 인덱스를 빠르게 조회하기 위해 set에 저장
    spoiled_indices = [False] * len(message)
    for start, end in spoiler_ranges:
        for i in range(start, end + 1):
            spoiled_indices[i] = True
            
    # 2. 정규표현식을 사용해 단어(공백이 아닌 부분)와 그 시작/끝 인덱스를 추출
    # 예: "hello world" -> [("hello", 0, 4), ("world", 6, 10)]
    words_info = []
    for m in re.finditer(r'\S+', message):
        words_info.append((m.group(), m.start(), m.end() - 1))
    
    # 3. 가려지지 않은(온전한) 단어들을 먼저 '아는 단어' 셋에 추가
    known_words = set()
    for word, start, end in words_info:
        # 단어의 범위 내에 스포일러 인덱스가 하나도 없는지 검사
        is_spoiled = any(spoiled_indices[i] for i in range(start, end + 1))
        if not is_spoiled:
            known_words.add(word)
            
    # 4. 전체 단어를 순서대로 확인하며 새로운 단어 카운트
    # (이미 아는 단어 셋을 실시간으로 업데이트하여 중복 방지)
    result = 0
    for word, start, end in words_info:
        if word not in known_words:
            result += 1
            known_words.add(word) # 이제 이 단어도 알게 됨
            
    return result