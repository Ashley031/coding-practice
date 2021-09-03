import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {

        int n, x;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> arr = new PriorityQueue<>(Collections.reverseOrder());

        /*
        * x == 0 : 배열에서 가장 큰 값을 출력하고 그 값을 배열에서 제거하는 경우
        * x == 자연수 : 배열에 x라는 값을 넣는(추가하는) 연산
        */

        for(int i=0 ; i<n ; i++) {
            x = Integer.parseInt(br.readLine());
            if(x == 0) {    // print the largest number
                sb.append(arr.size() == 0 ? 0 : arr.poll()).append('\n');
            }
            else{
                arr.add(x);
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
}
