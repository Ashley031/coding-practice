package Tree.IndexedTree.BOJ2042;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K, S;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        S = 1;
        while(S < N)
            S *= 2;
        tree = new long[2*S];

        long num;
        for(int i=S ; i<S+N ; i++){
            num = Long.parseLong(br.readLine());
            tree[i] = num;
        }

        for(int i=S-1 ; i>0 ; i--){
            tree[i] = tree[i*2] + tree[i*2+1];
        }

        long a,b,c;
        for(int i=1 ; i<=M+K ; i++){
            st = new StringTokenizer(br.readLine());
            a = Long.parseLong(st.nextToken());
            b = Long.parseLong(st.nextToken());
            c = Long.parseLong(st.nextToken());

            if(a == 1){ // update
                update(b,c);
            } else {    // query
                System.out.println(query(b,c));
            }
        }

        br.close();
    }

    // BU
    static void update(long target, long value) {
        long node = S + target - 1;
        tree[(int) node] = value;

        node /= 2;

        while(node >= 1) {
            tree[(int) node] = tree[(int) (node*2)] + tree[(int) (node*2+1)];
            node /= 2;
        }
    }

    // BU
    static long query(long left, long right) {
        long sum = 0;
        int leftNode = (int) (S + left - 1);
        int rightNode = (int) (S + right - 1);

        while(leftNode <= rightNode) {
            if(leftNode % 2 == 1){
                sum += tree[leftNode];
                leftNode = (leftNode+1)/2;
            } else if(leftNode % 2 == 0){
                leftNode /= 2;
            }

            if(rightNode % 2 == 1){
                rightNode /= 2;
            } else if(rightNode % 2 == 0){
                sum += tree[rightNode];
                rightNode = (rightNode-1)/2;
            }
        }

        return sum;
    }
}
