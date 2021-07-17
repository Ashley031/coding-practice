#include <iostream>
#include <string>
#include <stack>
using namespace std;
stack<char> st;

int solution(string s)
{
	int answer = -1;
	for (int i = 0; i < s.length(); i++) {
		if (st.empty() || st.top() != s[i])
			st.push(s[i]);
		else if (st.top() == s[i])
			st.pop();
	}

	// [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
	//	cout << "answer : " << answer << endl;

	if (st.empty()) answer = 1;
	else answer = 0;

	return answer;
}

int main() {

	string str = "";

	cin >> str;
	solution(str);
}