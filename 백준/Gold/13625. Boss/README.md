# [Gold III] Boss - 13625 

[문제 링크](https://www.acmicpc.net/problem/13625) 

### 성능 요약

메모리: 34124 KB, 시간: 400 ms

### 분류

너비 우선 탐색, 깊이 우선 탐색, 다이나믹 프로그래밍, 그래프 이론, 그래프 탐색

### 제출 일자

2025년 4월 17일 20:13:59

### 문제 설명

<p>Everyone knows Iks, the last trend in social network, which made so much success that competitors like Facebook and Google+ are strugling to keep their own social networks in business. As several “.com” companies, Iks started in a small garage, but today employs hundreds of thousands.</p>

<p>Iks has also a non-standard management system. For example, there are no committees or boards, which means less time spent on internal meetings. However, as usual in other companies, there is a chain (or rather several chains) of command, as a person may manage other employees, and may be managed by other employees. The figure below shows the chain of command for some employees, along with their ages.</p>

<p style="text-align:center"><img alt="" src="https://onlinejudgeimages.s3.amazonaws.com/problem/13625/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202017-01-05%20%EC%98%A4%ED%9B%84%208.35.00.png" style="height:181px; width:612px"></p>

<p>A person P<sub>1</sub> may manage another person P<sub>2</sub> directly (when P<sub>1</sub> is the immediate superior of P<sub>1</sub>) or indirectly (when P<sub>1</sub> manages direclty a person P<sub>3</sub> who manages P<sub>2</sub> directly or indirectly). For example, in the figure above, Alice manages David directly and Claire indirectly. A person does not manage himself/herself, either directly or indirectly.</p>

<p>One folklore that developed in Wall Street is that Iks is so successfull because in its chain of command a manager is always younger than the managed employee. As we can see in figure above, that is not true. But this folklore prompted Iks to develop a tool to study its own management system, and to understand whether age plays a role in its success. You have been hired to work on that tool.</p>

<p>Given the description of the chain of command at Iks and the ages of its employees, write a program that answers a series of instructions. Instructions are of two types: management change and query. An instruction of management change swaps the positions of two employees A and B. As an example, figure (b) above shows the resulting chain of command when David and George change their respective positions in the chain of command. A query instruction names one employee A and asks the age of the youngest manager of A; that is, the youngest person in the chain of command at Iks that manages A, either directly or indirectly. For example, in figure (a) above the youngest manager of Clara is 18 years old; in figure (b), the youngest manager of Clara is 21 years old.</p>

### 입력 

 <p>Each test case is described using several lines. The first line of a test case contains three integers N, M and I, representing respectively the number of employees, the number of direct manage relations and the number of instructions. Employees are identified by numbers from 1 to N. The second line contains N integers K<sub>i</sub>, where K<sub>i</sub> indicates the age of the employee number i.</p>

<p>Each of the following M lines contains two integers X and Y, indicating that X manages Y directly. Then it follows I lines, each describing an instruction. An instruction of type management change is described by a line containing the identifier T followed by two integers A and B, indicating the two employers that must swap places in the chain of command. An instruction of type query is described by a line containing the identifier P followed by one integer E, representing the number of an employer. The last instruction is of type query.</p>

<p>Restrictions</p>

<ul>
	<li>1 ≤ N ≤ 500</li>
	<li>0 ≤ M ≤ 60 × 10<sup>3</sup></li>
	<li>1 ≤ I ≤ 500</li>
	<li>1 ≤ K<sub>i</sub> ≤ 100, for 1 ≤ i ≤ N</li>
	<li>1 ≤ X, Y ≤ N, X ≠ Y</li>
	<li>1 ≤ A, B ≤ N</li>
	<li>1 ≤ E ≤ N</li>
</ul>

### 출력 

 <p>For each instruction of type query your program must print a single line containing a single integer, the age of the employee who is the youngest person that manages the employer named in the query (directly or indirectly), if that person exists. If the employee does not have a manager, print an * (asterisc).</p>

