#include <stdio.h>
#include <conio.h>
#include <string.h>
#include <stdlib.h>
//a
void docFile(FILE *f, char *s)
{
	
	f = fopen("bai1.txt","r");
	if(f == NULL)
	{
		printf("loi tao hoac mo file");
		exit(1);
	}
	fputs(s,f);
	fclose(f);
}

//b
void docIn(FILE *f, char *s)
{
	
	f = fopen("bai1.txt","w");
	if(f == NULL)
	{
		printf("loi tao hoac mo file");
		exit(1);
	}
	fgets(s, 100, f);
	puts(s);
	fclose(f);
}

//c
void docInNoi(FILE *f, char *s)
{
	
	f = fopen("bai1.txt","a");
	if(f == NULL){
		printf("loi tao hoac mo file");
		exit(1);
	}
	char d[100];
	gets(d);
	strcat(s,d);
	fgets(s,100,f);
	fprintf(f,"%s",s);
	puts(s);
}
int main()
{
	char s[100];
	FILE *f;
	gets(s);
	docFile(f,s);
	docIn(f,s);
	docInNoi(f,s);
	return 0;
}



