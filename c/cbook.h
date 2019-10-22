#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>


char createBook(){

char bname[100];
char text[100];
int id,max,i,j;
char buf[50];
char ch;

printf("\nPlease enter book name:");
scanf ("%s",bname);

FILE *createfile,*checkfile;

//check file max id

if ((checkfile = fopen("bcount.txt","r")) == NULL){
       printf("Error! opening file");
       // Program exits if the file pointer returns NULL.
       exit(1);
   }

fscanf(checkfile,"%s",text);











}
