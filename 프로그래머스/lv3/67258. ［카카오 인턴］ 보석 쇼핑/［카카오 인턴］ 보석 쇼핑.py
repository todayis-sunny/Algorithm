def solution(gems):
    gem = list(set(gems))
    gem_cnt = len(gem)
    
    a = 0
    b = 0
    length = 1e9
    answer = [0, 0]
    limit = len(gems)
    dic = {}
    
    while b < limit:
        if gems[b] not in dic:
            dic[gems[b]] = 1
        else:
            dic[gems[b]] += 1
            
        b += 1
        
        if len(dic) == gem_cnt:
            while a < b:
                if dic[gems[a]] > 1:
                    dic[gems[a]] -= 1
                    a += 1
                elif b-a < length:
                    if b-a == gem_cnt:
                        return [a+1, b]
                    length = b - a
                    answer = [a+1, b]
                    break
                else:
                    break

    return answer