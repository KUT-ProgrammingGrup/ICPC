#include "stdio.h"
#include "math.h"
#define N 123456

int main() {
    int i = 0;
    int j = 0;
    int count = 0;

    int judge[N*2];
    for (i = 0; i < N*2; i++) {
        judge[i] = 1;
    }
    for (i = 2; i < sqrt(N*2); i++) {
        if (judge[i]) {
            for (j = 0; i*(j+2) < N*2; j++) {
                judge[i*(j+2)] = 0;
            }
        }
    }

    int input;
    scanf("%d", &input);

    while(input) {
        for (i = input+1; i < input*2; i++) {
            if(judge[i])
                count++;
        }
        if (input == 1)
            count++;
        printf("%d\n", count);
        scanf("%d", &input);
        count = 0;
    }
    return 0;
}
