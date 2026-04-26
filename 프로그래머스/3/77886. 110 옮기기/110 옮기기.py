from collections import deque

def solution(s):
    answer = []
    for string in s:
        stack, cnt = find110s(string)
        answer.append(insert110s(stack, cnt))
    return answer

def find110s(s):
    stack = ""
    cnt = 0
    
    for ch in s:
        stack += ch
        while len(stack) > 2 and stack[-3:] == "110":
            stack = stack[:-3]
            cnt += 1
    
    return stack, cnt

def insert110s(s, cnt):
    length = len(s)
    
    for i in range(length - 1, -1, -1):
        if s[i] == '0':
            return s[:i + 1] + "110" * cnt + s[i + 1:]
        
    return "110" * cnt + s