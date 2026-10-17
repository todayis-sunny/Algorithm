from heapq import heappop, heappush

def solution(book_time):
    result = 1
    
    # "HH:MM" → HH * 60 + MM
    bookTimeRef = [(int(s[:2]) * 60 + int(s[3:]), int(e[:2]) * 60 + int(e[3:])) for s, e in book_time]
    bookTimeRef.sort()
    
    hQ = []
    for s, e in bookTimeRef:
        if not hQ:
            heappush(hQ, e + 10)
            continue
        if hQ[0] <= s:
            heappop(hQ)
        else:
            result += 1
            
        heappush(hQ, e + 10)
    
    return result