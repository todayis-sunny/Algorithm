def solution(s):
    for a in range(len(s), 0, -1):
        start = 0
        end = a -1
        while end < len(s):
            if isPalindrome(s, start, end):
                return a
            start += 1
            end += 1
    return 1   

def isPalindrome(s, start, end):
    for i in range((end - start) // 2 + 1):
        if s[start + i] != s[end - i]:
            return False
    return True