package BOJ17129;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();

        StringTokenizer st = new StringTokenizer(str);
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String addrPass, address, password, searchAdd, result;
        Map<String, String> searchTable = new HashMap<>();

        // Insertion
        for(int i=0 ; i<N ; i++){
            addrPass = br.readLine();
            st = new StringTokenizer(addrPass);
            address = st.nextToken();
            password = st.nextToken();
            searchTable.put(address, password);
        }

        // SEARCH
        for(int i=0 ; i<M ; i++){
            searchAdd = br.readLine();
            result = searchTable.get(searchAdd);
            sb.append(result).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
