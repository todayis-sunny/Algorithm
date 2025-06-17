def solution(s, skip, index):
    answer = ""
    alpha = "abcdefghijklmnopqrstuvwxyz" 
    
    for pwd in skip:
        if pwd in alpha:
            alpha = alpha.replace(pwd, "")
    
    for i in s:
        change = alpha[(alpha.index(i) + index) % len(alpha)]
        answer += change
    
    return answer