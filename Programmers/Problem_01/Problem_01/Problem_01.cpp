#include <string>
#include <iostream>
#include <vector>

using namespace std;

string solution(int n) {

	vector<int> rem;
	int result;
	
	while (n > 0) {
		if (n % 3 == 0) {
			rem.push_back(4);
			n = n / 3 - 1;
		}
		else {
			rem.push_back(n % 3);
			n /= 3;
		}
	}

	string answer = "";

	for (int i = rem.size() - 1; i >= 0; i--) {
		answer += to_string(rem[i]);
	}
	return answer;
}

int main() {

	int n;
	cin >> n;

	string result = solution(n);

	cout << result;

	return 0;
}