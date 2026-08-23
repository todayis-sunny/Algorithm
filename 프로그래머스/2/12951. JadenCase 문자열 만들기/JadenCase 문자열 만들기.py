def solution(s):
    return " ".join([word[0].upper() + word[1:].lower() if word else word for word in s.split(" ")])
    
def solution(s):
    result = []
    s = s.split(" ")
    for word in s:
        if word:
            result.append(word[0].upper() + word[1:].lower())
        else:
            result.append(word)
    return " ".join(result)