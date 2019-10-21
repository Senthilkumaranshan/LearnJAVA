#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include "book.h"


int main(){

char n;
printf("\n    Actions\t\tKeys\n");
printf("---------------------------\n");
printf("\n Manage Books\t\t b\n");
printf("\n Manage Staffs\t\t s\n");
printf("\n Manage Members\t\t m\n");
printf("\nPlease type a key and press enter:");
scanf("%c",&n);
char selection =tolower(n);

switch(selection)
     {
         case 'b':
           printf("Case1: Value is: %c", selection);
           bookmenu();
           break;
         case 's':
           printf("Case2: Value is: %c", selection);
	   break;
         case 'm':
           printf("Case3: Value is: %c", selection);
	   break;
         default:
           printf("Ivalid Selection");
    }
 
return 0;

}
