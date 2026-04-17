def solution(wallpaper):
    result = [-1, 50, -1, -1]
    
    for i in range(len(wallpaper)):
        if '#' in wallpaper[i]:
            if result [0] == -1:
                result[0] = i
            result[2] = i + 1
        for j in range(len(wallpaper[0])):
            if wallpaper[i][j] == '#':
                if j < result[1]:
                    result[1] = j
                if result[3] < j + 1:
                    result[3] = j + 1

    return result