# 1983. 조교의 성적 매기기 <D2>
 
T = int(input())
grade_L = ['A+', 'A0', 'A-', 'B+', 'B0', 'B-', 'C+', 'C0', 'C-', 'D0']
 
for t in range(1,T+1):
    arr = []
    N, k = map(int, input().split())
     
    for _ in range(N):
        middle, final, task = map(int, input().split())
        grade = 0.35*middle + 0.45*final + 0.20*task
        arr.append(grade)
         
    k_grade = arr[k-1]
    arr.sort(reverse=True)
    print('#'+str(t), grade_L[arr.index(k_grade)//(N//10)])