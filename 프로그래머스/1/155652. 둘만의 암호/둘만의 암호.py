def solution(s, skip, index):
    ans = ""
    alpha = "abcdefghijklmnopqrstuvwxyz" 
    
    for pwd in skip:
        if pwd in alpha:
            alpha = alpha.replace(pwd, "")
    
    for i in s:
        change = alpha[(alpha.index(i) + index) % len(alpha)]
        ans += change
    
    return ans