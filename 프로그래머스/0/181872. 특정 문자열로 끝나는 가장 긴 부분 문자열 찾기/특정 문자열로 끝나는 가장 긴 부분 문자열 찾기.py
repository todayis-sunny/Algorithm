def solution(myString, pat):
    end = myString.rfind(pat)
    
    return myString[:end + len(pat)]