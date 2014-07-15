#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <cmath>
#include <cctype>
#include <cstdint>
#include <cstring>
#include <cstdlib>

#include <iostream>
#include <algorithm>
#include <string>
#include <sstream>
#include <vector>
#include <map>
#include <stack>
#include <queue>
#include <list>
#include <functional>


using namespace std;


int do_A(int x, int y, int s){
	int tmp = 0;
	int max = 0;
	//int maxfix = 0;

	int ay = (100 + y);
	int ax = (100 + x);

	//cout << ax << " " << ay << endl;
	for (int i = 1; i < s; i++)
	{
		for (int j = 1; j < s; j++)
		{
			if (i * ax / 100 + j * ax / 100  == s)
			{
				
				tmp = i * ay / 100 + j * ay / 100;
				
				//if (i == 25 && j == 75)
				//{
					//cout << int(i * ay) << ", " << i*ay << ", " <<ay << ", "
					//<< int(j * ay) << ", " << j*ay
					//<< ", " << tmp << endl;
					//cout << endl << int(i*ay) << "." << int(j*ay) << endl;
				//}
				if (max < tmp) max = tmp;
				//if (tmp == 115) cout << "*" << int(i * ay) << " " << int(j * ay);
			}
		}
	}

	return max;
}



int main(void)
{
	int x, y, s;

	while (true)
	{
		cin >> x >> y >> s;
		if (x == 0) break;
		cout << do_A(x, y, s) << endl;
	}
	return 0;
}