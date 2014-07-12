#include <iostream>
#include <algorithm>

using namespace std;

int tax(int x, int p) {
	return p * (100 + x) / 100;
}

int solve(int x, int y, int s) {
	int max_val = 0;

	for (int i = 1; i < s; ++i) {
		for (int j = 1; j < s; ++j) {
			if (tax(x, i) + tax(x, j) == s){
				max_val = max(max_val, tax(y, i) + tax(y, j));
			}
		}
	}

	return max_val;
}

int main(void) {
	int x, y, s;

	while (cin >> x >> y >> s, x > 0) {
		cout << solve(x, y, s) << endl;
	}

	return 0;
}