#include <iostream>
#include <vector>

using namespace std;

void initData(int field[][5], int height){
	for(int i = 0; i < height; i++){
		for(int j = 0; j < 5; j++){
			cin >> field[i][j];
		}
	}
}

void printState(int field[][5], int height){
	for(int i = 0; i < height; i++){
		for(int j = 0; j < 5; j++){
			cout << field[i][j] << " ";
		}
		cout << endl;
	}
}


int deleteStone(int field[][5], int height){
	int x;
	int posx;
	int count = 0;

	int point = 0;

	for(int i = height - 1; i >= 0; i--){
		x = field[i][0];
		posx = 0;
		count = 1;
		for(int j = 1; j <= 5; j++){
			if((x == field[i][j] && field[i][j] != 0) && j != 5){
				count++;
			}else{
				if(count >= 3) {
					for(int c = posx; c < j; c++){
						point = point + field[i][c];
						field[i][c] = 0;
					}
				}
				count = 1;
				posx = j;
				x = field[i][j];
			}
		}
	}
	//printState(field, height);
	//cout << endl;
	for(int i = 0; i < 5; i++){
		for(int j = 1; j < height; j++){
			if(field[j][i] == 0){
				for(int c = j - 1; c >= 0; c--){
					field[c+1][i] = field[c][i];
				}
				field[0][i] = 0;
			}
		}
	}
	//printState(field, height);
	//cout << "==============" << endl;
	return point;
}



int do_problemB(const int height){
	const int width = 5;
	int field[height][5];
	int ret = 0;
	int point = 0;

	initData(field, height);

	while((point = deleteStone(field, height)) != 0){
		ret += point;
	}

	return ret;
}


int main(){
	int height;
	while(true){
		cin >> height;
		if(height == 0) break;
		cout << do_problemB(height) << endl;
	}

	return 0;
}
