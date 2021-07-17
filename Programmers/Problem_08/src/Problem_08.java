
public class Problem_08 {
	
	class Solution {
	}
	
	public static String solution(String S, String C) {
		String com = "@" + C.toLowerCase() + ".com";
		String answer = "";
		String[] name, tmp, tmp2;
		
		name= S.split(";");
		String[] addr = new String[name.length];
		
		int cnt = 0;
		while(cnt < name.length) {
			tmp = name[cnt].split(" ");
			/* Test */
			for(int i=0 ; i<tmp.length ; i++)
				tmp[i] = tmp[i].replace("-", "");
			
			if(cnt != 0)
				addr[cnt] = tmp[tmp.length-1].toLowerCase() + "_" + tmp[1].toLowerCase() + com;
			else
				addr[cnt] = tmp[tmp.length-1].toLowerCase() + "_" + tmp[0].toLowerCase() + com;
			cnt++;
		}
		
		
		for(int i=0 ; i<addr.length-1; i++) {
			cnt = 1;
			for(int j=i+1 ; j<addr.length ; j++) {
				if(addr[i].equals(addr[j])) {
					cnt++;
					tmp2 = addr[j].split("@");
					addr[j] = tmp2[0] + Integer.toString(cnt)+ "@" + tmp2[1];	
				}
			}
		}
		
		for(int i=0 ; i<addr.length ; i++) {
			answer += name[i] + " <" + addr[i] + ">;";
		}
		answer = answer.substring(0, answer.length()-1);
		
		return answer;
	}
	
	public static void main(String[] args) {
		String S = "John Doe; Peter Benjamin Parker; Mary Jane Watson-Parker; John Elvis Doe; John Evan Doe; Jane Doe; Peter Brian Parker";
		String C = "Example";
		String answer = solution(S,C);
		System.out.println(answer);
	}

}
