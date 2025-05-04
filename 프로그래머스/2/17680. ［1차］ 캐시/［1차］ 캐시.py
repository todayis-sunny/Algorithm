from collections import deque

def solution(cacheSize, cities):
    cache = deque()
    time = 0
    for city in cities:
        city = city.lower() # 대소문자 구분을 하지 않는다.
        if cacheSize:
            if not city in cache:
                if len(cache) == cacheSize:
                    cache.popleft()
                cache.append(city)
                time += 5
            else:
                cache.remove(city)
                cache.append(city)
                time += 1
        else:
            time += 5
    return time