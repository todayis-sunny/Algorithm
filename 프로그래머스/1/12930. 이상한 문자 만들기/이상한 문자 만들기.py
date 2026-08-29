def solution(s):
    result = ""
    arr = s.split(" ")
    for s in arr:
        for i, x in enumerate(s):
            result += x.upper() if i % 2 == 0 else x.lower()
        result += " "
    return result[:-1]