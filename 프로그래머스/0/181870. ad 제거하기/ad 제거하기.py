def solution(strArr):
    answer = []
    for ad in strArr:
        if not "ad" in ad:
            answer.append(ad)
        
    return answer