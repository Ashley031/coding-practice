package Tree.IndexedTree.BOJ7578;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int N, S;
    static long[] tree;
    static int[] A;
    static HashMap<Integer, Integer> B;
    static long answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        A = new int[N+1];
        B = new HashMap<>();

        S = 1;
        while(S < N)
            S *= 2;
        tree = new long[2*S];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1 ; i<=N ; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1 ; i<=N ; i++) {
            B.put(Integer.parseInt(st.nextToken()), i);
        }

        int index;
        answer = 0;
        for(int i=1 ; i<=N ; i++){
            index = B.get(A[i]);
            answer += query(index+1, N);
            update(index, 1);
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void update(int index, int value) {
        index = S + index - 1;
        tree[index] = value;
        index /= 2;

        while(index >= 1){
            tree[index] = tree[index*2] + tree[index*2+1];
            index /= 2;
        }
    }

    static long query(int left, int right) {
        long sum = 0;

        int leftNode = S + left - 1;
        int rightNode = S + right - 1;

        while(leftNode <= rightNode) {
            if(leftNode % 2 == 1) {
                sum += tree[leftNode];
                leftNode++;
            }
            if(rightNode % 2 == 0) {
                sum += tree[rightNode];
                rightNode--;
            }

            leftNode /= 2;
            rightNode /= 2;
        }

        return sum;
    }
}
