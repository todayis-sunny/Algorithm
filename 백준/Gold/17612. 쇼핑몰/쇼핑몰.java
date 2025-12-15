// 17612. [G2] 쇼핑몰.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, K;
    static long ans = 0;
    static ShoppingMall shoppingMall;
    static Customer[] customers;


    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        shoppingMall = new ShoppingMall();
        customers = new Customer[N];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            customers[n] = new Customer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    static void solve() {
        for (Customer customer : customers) {
            shoppingMall.addCustomer(customer);
        }
        shoppingMall.allClearCounter();
    }

    static void output() throws IOException {
        bw.write(String.valueOf(shoppingMall.ans));
        bw.flush();
    }

    static class ShoppingMall {
        Stack<Integer> customerStack = new Stack<>();
        Queue<Integer> counterQueue = new LinkedList<>();
        PriorityQueue<Counter> pq = new PriorityQueue<>(Comparator.comparing(Counter::getEndTime).thenComparing(Counter::getId));
        int currTime = 0;
        int sequence = 1;
        long ans = 0;

        public ShoppingMall() {
            for (int k = 0; k < K; k++) {
                counterQueue.offer(k);
            }
        }

        void clearCounter() {
            // 아직 비어있는 계산대가 있는 경우는 종료
            if (!counterQueue.isEmpty()) return;
            do {
                Counter counter = pq.poll();
                currTime = counter.endTime;
                customerStack.push(counter.customerId); // 같은 종료시간에서 계산대 번호가 낮은 순으로 나오기 때문에 스택으로 순서 변경
                counterQueue.offer(counter.id);
            } while (!pq.isEmpty() && currTime == pq.peek().endTime);

            while (!customerStack.isEmpty()) {
                ans += (long) sequence++ * customerStack.pop();
            }
        }

        void allClearCounter() {
            int timeKey = 0;
            while (!pq.isEmpty()) {
                while (!pq.isEmpty() && timeKey == pq.peek().endTime) {
                    Counter counter = pq.poll();
                    currTime = counter.endTime;
                    customerStack.push(counter.customerId); // 같은 종료시간에서 계산대 번호가 낮은 순으로 나오기 때문에 스택으로 순서 변경
                }
                while (!customerStack.isEmpty()) {
                    ans += (long) sequence++ * customerStack.pop();
                }
                if (!pq.isEmpty()) {
                    timeKey = pq.peek().endTime;
                }
            }
        }

        void addCustomer(Customer customer) {
            clearCounter();
            Counter counter = new Counter(counterQueue.poll(), currTime, customer);
            pq.offer(counter);
        }
    }

    static class Counter {
        int id; // 계산대 id
        int endTime; // 계산 종료 시간
        int customerId; // 고객의 id

        public Counter(int id, int currTime, Customer customer) {
            this.id = id;
            this.endTime = currTime + customer.cart;
            this.customerId = customer.id;
        }

        public int getEndTime() {
            return endTime;
        }

        public int getId() {
            return id;
        }
    }

    static class Customer {
        int id; // 고객의 id
        int cart; // 고객의 카트에 담긴 물건의 양

        public Customer(int id, int cart) {
            this.id = id;
            this.cart = cart;
        }
    }
}
