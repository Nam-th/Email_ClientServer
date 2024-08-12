#include<stdio.h>
#include<math.h>
#include<malloc.h>
//a
void nhapmang(float *a, int m, int n)
{
	for(int i=0; i<m; i++)
	{
		for(int j=0; j<n; j++)
		{
			printf("a[%d][%d] = ", i, j);
			scanf("%f", (a+i*n+j));
		}
	}
}
//b

//void xuatmangnguyen(int *a, int m, int n)
//{
//	for(int i=0; i<m; i++)
//	{
//		for(int j=0; i<n; j++)
//		{
//			printf("%5d", *(a+i*n+j));
//		}
//		printf("\n");
//	}
//}
//c
void xuatmangthuc(float *a, int m, int n)
{
	for(int i=0; i<m; i++)
	{
		for(int j=0; j<n; j++)
		{
			printf("%5f", *(a+i*n+j));
		}
		printf("\n");
	}
}
//d
float tong(float *a, int m, int n)
{
	float tong=0;
	for(int i=0; i<m; i++)
		for(int j=0; j<n; j++)
			tong+=*(a+i*n+j);
	return tong;
}
//e
int tongchan(float *a, int m, int n)
{
	int tong=0;
	for(int i=0; i<m; i++)
		for(int j=0; j<n; j++)
			if(*(a+i*n+j)%2==0)
				tong+=*(a+i*n+j);
	return tong;
}
//f
int tongle(float *a, int m, int n)
{
	int tong=0;
	for(int i=0; i<m; i++)
		for(int j=0; j<n; j++)
			if(*(a+i*n+j)%2!=0)
				tong+=*(a+i*n+j);
	return tong;
}
//g
int KTNT(int n)
{
	if(n<2)
		return 0;
	if(n==2)
		return 1;
	for(int i=2; i<=sqrt(n); i++)
		if(n%i==0)
			return 0;
		return 1;
}
int tongNT(float *a, int m, int n)
{
	int tong=0;
	for(int i=0; i<m; i++)
		for(int j=0; j<n; j++)
			if(KTNT(*(a+i*n+j)))
				tong+=*(a+i*n+j);
	return tong;
}
//h
float tongDCC(float *a, int m, int n)
{
	float tong=0;
	for(int i=0; i<m; i++)
		for(int j=0; j<n; j++)
			if(i=j)
				tong+=*(a+i*n+j);
	return tong;
}
//i
//float tongDCP(float *a, int m, int n)
//{
//	float tong=0;
//	for(int i=0; i<m; i++)
//		for(int j=0; j<n; j++)
//			if(i+j)
//}

//j
//k
int firstchan(int *a, int m, int n)
{
	for(int i=0; i<m; i++)
		for( int j=0; j<n; j++)
			if(*(a+i*n+j)%2==0)
				return *(a+i*n+j);
			return -1;
}
int main()
{
	int m,n;
	float *a = (float *)malloc(100*100);
	do
	{
		printf("nhap vao so dong: ");
		scanf("%d",&m);
		printf("nhap vao so cot: ");
		scanf("%d", &n);
		if(m<=0 || m>=100)
			printf("\nnhap m thoa 0<m<100");
		if(n<=0 || n>=100)
			printf("\nnhap m thoa 0<m<100");
	}while((m<=0 || m>=100)||(n<=0 || n>=100));
	nhapmang(a,m,n);
//	printf("\nmang nguyen: \n");
//	xuatmangnguyen(a,m,n);
	printf("\nmang thuc: \n");
	xuatmangthuc(a,m,n);
	printf("\ntong cac phan tu: %f", tong(a,m,n));
	printf("\ntong cac phan chan tu: %d", tongchan(a,m,n));
	printf("\ntong cac phan le tu: %d", tongle(a,m,n));
	printf("\ntong cac phan tu nguyen to: %d", tongNT(a,m,n));
	printf("\ntong cac phan tu tren dcc: %f", tongDCC(a,m,n));
	printf("\ntong cac phan tu tren dcp: %f", tongDCP(a,m,n));
	if(firstchan(a,m,n)==-1)
		printf("\nkhong co phan tu chan");
	else
		printf("\nphan tu chan dau tien la: %d", firstchan(a,m,n));
}

