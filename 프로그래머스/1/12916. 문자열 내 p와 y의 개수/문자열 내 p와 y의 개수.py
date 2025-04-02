def solution(s):
    # 소문자로 치환
    s = s.lower()
    if s.count("p") != s.count("y"):
        return False
    return True