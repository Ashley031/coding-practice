import java.io.*;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        int n, x;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> arr = new PriorityQueue<>();

        n = Integer.parseInt(br.readLine());
        for(int i=0 ; i<n ; i++){
            x = Integer.parseInt(br.readLine());
            if(x == 0)
                sb.append(arr.size() == 0 ? 0 : arr.poll()).append('\n');
            else
                arr.add(x);
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }
}
