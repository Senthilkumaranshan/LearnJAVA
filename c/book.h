#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include "cbook.h"

char bookmenu(){
char b;
printf("                     \n");
printf("\n    Actions\t\tKeys\n");
printf("---------------------------\n");
printf("\n View Books\t\t v\n");
printf("\n Create Book\t\t c\n");
printf("\n Update Book\t\t u\n");
printf("\n Delete Book\t\t d\n");
printf("\nPlease type a key and press enter:");
scanf(" %c",&b);
char bselection =tolower(b);

switch(bselection)
     {
         case 'v':
           printf("Case1: Value is: %c", bselection);
           bookmenu();
           break;
         case 'c':
           printf("Case2: Value is: %c", bselection);
	   createBook();
	   break;
         case 'u':
           printf("Case3: Value is: %c", bselection);
	   break;
	 case 'd':
           printf("Case4: Value is: %c", bselection);
	   break;
         default:
           printf("Ivalid Selection");
    }


}
