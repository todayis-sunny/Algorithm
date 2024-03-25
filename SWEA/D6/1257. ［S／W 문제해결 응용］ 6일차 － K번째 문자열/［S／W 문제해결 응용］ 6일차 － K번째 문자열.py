# 1257. K번째 문자열.

for tc in range(1, int(input()) + 1):
    K = int(input())
    data = input()
    strSet = set()
    dataLength = len(data)
    for length in range(1, dataLength + 1):
        for i in range(dataLength - length + 1):
            strSet.add(data[i:i+length])
    dataList = sorted(list(strSet))
    if K-1 > len(dataList):
        print(f"#{tc} none")
    else:
        print(f"#{tc} {dataList[K-1]}")
