#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>


char createBook(){

char bname[100];
char text[100];
int id,max,i;
char buf[50];

printf("\nPlease enter book name:");
fgets(bname,100,stdin);;

//to uppercase
for(i=0; bname[i]!='\0'; i++)
    {
        /*
         * If current character is lowercase alphabet then
         * convert it to uppercase.
         */
        if(bname[i]>='a' && bname[i]<='z')
        {
            bname[i] = bname[i] - 32;
        }
    }

FILE *createfile,*checkfile;

//check file max id

if ((checkfile = fopen("./books/books.txt","r")) == NULL){
       printf("Error! opening file");
       // Program exits if the file pointer returns NULL.
       exit(1);
   }

fscanf(checkfile,"%s",text);

while (fgets(text, 100, checkfile) != NULL){
    while (text[i] != ' '){
	
	if(text[i]>max){
	max = text[i];
        }
        i++;
    }

    i=0;
}

max = max +1;


fopen("./books/books.txt","w");

if(createfile == NULL)
   {
      printf("Error!");   
      exit(1);             
   }

snprintf(buf, 12, bname, max); // puts string into buffer

fprintf(createfile,"%s",buf);


fclose(createfile);




}
