def solution(n, lost, reserve):
    rev_student = set(reserve) - set(lost)
    
    lost_student = set(lost) - set(reserve)
    
    for student in rev_student:
        if (student - 1) in lost_student:
            lost_student.remove(student - 1)
        elif (student + 1) in lost_student:
            lost_student.remove(student + 1)
            
    return n - len(lost_student)