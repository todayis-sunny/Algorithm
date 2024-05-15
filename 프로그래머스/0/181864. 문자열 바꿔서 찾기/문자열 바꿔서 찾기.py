def solution(myString, pat):
    pat = pat.replace('A', 'C').replace('B', 'A').replace('C', 'B')

    if pat in myString:
        return 1
    return 0