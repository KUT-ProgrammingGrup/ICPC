/*	ICPC 2014 
 *	Probrem D
 *	URL: http://icpc.iisf.or.jp/2014-waseda/domestic/problems/
 *============================================================*/

#include <iostream>
#include <list>
#include <string>

using namespace std;


int decoding_counter = 0;
char* result1[5];
char* result2[5];

bool searchChar(list<char>& converted, char c){
	for(list<char>::iterator ite = converted.begin();
			 ite != converted.end(); ite++){
		if(*ite == c) return true;
	}
	return false;
}


void copy(list<char>& converted, char* pchar){
	int count = 0;
	for(list<char>::iterator ite = converted.begin(); 
			ite != converted.end(); ite++){
		pchar[count++] = *ite;
	}
	pchar[count] = '\0';
}


int cnt = 0;
void save(list<char>& c){
	if(decoding_counter < 5){
		copy(c, result1[decoding_counter]);
	}else{
		copy(c, result2[cnt++]);
		if(cnt >= 5) cnt = 0;
	}
}


void do_probremD(char* buf, list<char>& converted, int count){
	char c = buf[count];
	if(c == '\0'){
		save(converted);
		decoding_counter++;
		return;
	}
	if(converted.empty()){
		if(c == 'a'){
			converted.push_back('a');
			do_probremD(buf, converted, count + 1);
			converted.pop_back();
			converted.push_back('b');
			do_probremD(buf, converted, count + 1);
			converted.pop_back();
		}else{
			converted.push_back(c+1);
			do_probremD(buf, converted, count + 1);
			converted.pop_back();
		}
		return;
	}

	if(!searchChar(converted, c)){
		if(c != 'z'){
			converted.push_back(c+1);
			do_probremD(buf, converted, count + 1);
			converted.pop_back();
		}
	}else{
		if(c == 'z'){
			converted.push_back(c);
			do_probremD(buf, converted, count + 1);
			converted.pop_back();
		}else{
			converted.push_back(c);
			do_probremD(buf, converted, count + 1);
			converted.pop_back();
			converted.push_back(c+1);
			do_probremD(buf, converted, count + 1);
			converted.pop_back();
		}
	}
	return;
}
	




int main(){
	char buf[21] = {0};
	list<char> converted;

	decoding_counter = 0;
	cnt = 0;

	for(int i = 0; i < 5; i++){
		result1[i] = new char[21];
		result2[i] = new char[21];
	}

	while(true){
		for(int i = 0; i < 21; i++){
			buf[i] = '\0';
		}
		cin >> buf;
		if(buf[0] == '#') break;
		do_probremD(buf, converted, 0);
		cout << decoding_counter << endl;
		if(decoding_counter < 10){
			int n;
			if(decoding_counter < 5) n = decoding_counter;
			else n = 5;
			for(int i = 0; i < n; i++){
				cout << result1[i] << endl;
			}
			for(int i = 0; i < cnt; i++){
				cout << result2[i] << endl;
			}
		}else{
			for(int i = 0; i < 5; i++){
				cout << result1[i] << endl;
			}
			for(int i = 0; i < 5; i++){
				cout << result2[cnt++] << endl;
				if(cnt >= 5) cnt = 0;
			}
		}
			
		decoding_counter = 0;
		cnt = 0;
	}
}
