/*======================================
 *	Millennium
 *	url: http://judge.u-aizu.ac.jp/onlinejudge/description.jsp?id=1179&lang=jp
 *	author: takafumi kataoka
 *	date: 2014.07.09
 ========================================*/


#include <iostream>

using namespace std;



int do_millennium(int y, int m, int d){
	int days = 0;
	int dai = (20 * 5) + (19 * 5);
	int syou = 20 * 10;
	for(int i = 1; i < y; i++){
		if(i % 3 == 0) days += syou;
		else days += dai;
	}

	
	if(y % 3 == 0) days += 20 * (m - 1);
	else{
		for(int i = 1; i < m; i++){
			if(i % 2 == 0) days += 19;
			else days += 20;
		}
	}
	days += d;

	return days;
}



int main(){
	int n;
	int y, m, d;
	int millennium = do_millennium(999, 10, 20) + 1;

	cin >> n;

	for(int i = 0; i < n; i++){
		cin >> y >> m >> d;
		cout << millennium - do_millennium(y, m, d) << endl;
	}

	return 0;
}
