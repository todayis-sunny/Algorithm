// 02461. [G1] 대표선수.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, left, right, min, max, diff, answer, ability, count;
    static Student[] students;
    static int[] groups;
    public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		students = new Student[N*M];
		groups = new int[N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				ability = Integer.parseInt(st.nextToken());
				students[n*M + m] = new Student(n, ability);
			}
		}
		
		Arrays.sort(students, Comparator.comparing(Student::getAbility));
		answer = Integer.MAX_VALUE;
		left = 0;
		right = 0;
		
		groups[students[left].group] += 1;
		count = 1; 
		while(left < N*M - 1 && right < N*M) {
			
			if (count < N) {
				if (right == N*M - 1) {
					break;
				}
				count = groups[students[++right].group]++ == 0 ? count + 1 : count;
			} else {
				min = students[left].ability;
				max = students[right].ability;
				diff = max - min;
				count = groups[students[left++].group]-- == 1 ? count - 1 : count;
				answer = Math.min(answer, diff);
			}
		}
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
    
    static class Student {
    	int group;
    	int ability;
		public Student(int group, int ability) {
			this.group = group;
			this.ability = ability;
		}
		public int getAbility() {
			return ability;
		}
    	
    }
}
