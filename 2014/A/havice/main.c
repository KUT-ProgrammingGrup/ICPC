#include <stdio.h>
#include <stdlib.h>
#define tax(p,x) (p*(100+x))/100

int conv(int x,int y,int s){
	int m = 0;
	for(int i = 1;i < s;i ++){
		for(int j = 1;j < s;j++){
			if(tax(i,x)+tax(j,x) == s && m < tax(i,y)+tax(j,y)){
				m = tax(i,y)+tax(j,y);
			}
		}
	}
	return m;
}

int main(){
	int x, y, s;
	while(1){
		scanf("%d%d%d",&x,&y,&s);
		if(x == 0 && y == 0 && s == 0){
			break;
		}
		printf("%d\n",conv(x,y,s));
	}
	
	return 0;
}
//vim: set ft=C fenc=utf-8 ff=unix ts=4 sw=4:

