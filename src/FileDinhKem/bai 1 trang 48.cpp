#include<stdio.h>
#include<math.h>
#include<malloc.h>
//a
void nhapmang(int *a, int n)
{
	for(int i=0; i<n; i++)
	{
		printf("a[%d] = ", i);
		scanf("%d", (a+i));
	}
} 
//b
void xuatmang(int *a, int n)
{
	for(int i=0; i<n; i++)
	{
		printf("%5d", *(a+i));
	}    
}
//c
int tong(int *a, int n)
{
	int tong = 0;
	for(int i=0; i<n; i++)
	{
		tong+=*(a+i);
	}
	return tong;
}
//d
int tongchan(int *a, int n)
{
	int tong = 0;
	for(int i=0; i<n; i++)
	{
		if(*(a+i) % 2 ==0)
			tong+=*(a+i);
	}
	return tong;
}
//e
int tongle(int *a, int n)
{
	int tong = 0;
	for(int i=0; i<n; i++)
	{
		if(*(a+i) % 2 !=0)
			tong+=*(a+i);
	}
	return tong;
}
//f
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
int tongNT(int *a, int n)
{
	int tong=0;
	for(int i=0; i<n; i++)
	{
		if(KTNT(*(a+i)))
			tong+=*(a+i);
	}
	return tong;
}
//g
int firstchan(int *a, int n)
{
	for(int i=0; i<n; i++)
	{
		if(*(a+i)%2==0)
			return *(a+i);
	}
	return -1;
}
//h
int firstle(int *a, int n)
{
	for(int i=0; i<n; i++)
	{
		if((*(a+i))%2!=0)
		return *(a+i);
	}
	return -1;
}
//i
int firstNT(int *a, int n)
{
	for(int i=0; i<n; i++)
	{
		if(KTNT(*(a+i)))
			return *(a+i);
	}
	return -1;                  
}
//j
int lastchan(int *a, int n)
{
	for(int i=n-1; i>=0; i--)
	{
		if(*(a+i)%2==0)
			return *(a+i);
	}
	return -1;
}
//k
int KTSCP(int n)
{
	if(n==sqrt(n)*sqrt(n))
		return 1;
	return 0;
}
int lastSCP(int *a, int n)
{
	for(int i=n-1; i>=0; i--)
	{
		if(KTSCP(*(a+i)))
			return *(a+i);
	}
	return -1;
}
//l
int max(int *a, int n)
{
	int max=*a;
	for(int i=0; i<n; i++)
	{
		if(*(a+i)>max)
			max=*(a+i);
	}
	return max;
}
//m
int demchan(int *a, int n)
{
	int d=0;
	for(int i=0; i<n; i++)
	{
		if(*(a+i)%2==0)
			d++;
	}
	return d;
}
//n
int demmax(int *a, int n)
{
	int d=0;
	for(int i=0; i<n; i++)
	{
		if(*(a+i)==max(a,n))
			d++;
	}
	return d;
}
//o
int inmax(int *a, int n)
{
	for(int i=0; i<n; i++)
	{
		if(*(a+i)==max(a,n))
			return i;
	}
}
//p
void themdau(int *a, int &n, int x)
{
	for(int i=n; i>0; i--)	
		*(a+i)=*(a+i-1);
		*a=x;
		n++;
		xuatmang(a,n);
}
//q
void themcuoi(int *a, int &n, int x)
{
//	for(int i=n; i>n-1; i--)
//		*(a+i)=*(a+i-1);
//		*(a+n-1)=x;
//		n++;
//		xuatmang(a,n);
	for(int i=n; i>0; i--)
		*(a+n)=x;
		n++;
		xuatmang(a,n);
}
//r
void themvt(int *a, int &n, int x, int k)
{
	for(int i=n; i>k; i--)
		*(a+i)=*(a+i-1);
		*(a+k)=x;
		n++;
		xuatmang(a,n);
}
//s
void xoak(int *a, int &n, int k)
{
	for(int i=k; i<n-1; i++)
		{
			*(a+i)=*(a+i+1);
		}
		n--;
}
int xoachandau(int *a, int n)
{
	int k;
    for(int  i=0 ; i<n; i++ )
        if( *(a+i)%2==0)
        {
		   	k=i;
			xoak(a,n,k);        
           	break;         
        }
      	xuatmang(a,n);
}
//t
void xoaallmax(int *a, int &n)
{
	int k;
	for(int i=0; i<n; i++)
	{
		if((*(a+i))==max(a,n))
		{
			k=i;
			xoak(a,n,k);
		}
	}
	xuatmang(a,n);
}
//u
void sapxep(int *a, int n)
{
	int doi;
	for(int i=0; i<n-1; i++)
		for (int j=i+1; j<n; j++)
		{
			if(*(a+i)>*(a+j))
			{
				doi=*(a+i);
				*(a+i)=*(a+j);
				*(a+j)=doi;
			}		
		}	
}
int main()
{
	int n, x, k;
//	int *a = (int *)malloc(n * sizeof(int));
	int *a=(int *)malloc(100);
	do
	{
		printf("nhap vao so phan tu: ");
		scanf("%d", &n);
		if(n<=0||n>=100)
			printf("\nnhap n thoa 0<n<100");
	}while(n<=0||n>=100);
	nhapmang(a,n);
	printf("\nmang vua nhap: ");
	xuatmang(a,n);	
	printf("\ntong cac phan tu: %d", tong(a,n));
	printf("\ntong cac phan tu chan: %d", tongchan(a,n));
	printf("\ntong cac phan tu le: %d", tongle(a,n));
	printf("\ntong cac phan tu nguyen to: %d", tongNT(a,n));
	if(firstchan(a,n)==-1)
		printf("\nkhong co phan tu chan");
	else
		printf("\nphan tu chan dau tien la: %d", firstchan(a,n));
		
	if(firstle(a,n)==-1)
		printf("\nkhong co phan tu le");
	else
		printf("\nphan tu le dau tien la: %d", firstle(a,n));
		
	if(firstNT(a,n)==-   iiiiiiiiiiiiiiiiiiiiiiii1)
		printf("\nko co phan tu nguyen to");
	else
		printf("\nphan tu nguyen to dau tien: %d", firstNT(a,n));
		
	if(lastchan(a,n)==-1)
		printf("\nkhong co phan tu chan");
	else
		printf("\nphan tu chan cuoi cung la: %d", lastchan(a,n));
	
	if(lastSCP(a,n)==-1)
		printf("\nko co so chinh phuong");
	else
		printf("\nphan tu chinh phuong cuoi cung: %d", lastSCP(a,n));
	printf("\nphan tu lon nhat: %d", max(a,n));
	
	if(demchan(a,n)==0)
		printf("\nko co phan tu chan");
	else
		printf("\nco %d phan tu chan", demchan(a,n));
		
	printf("\nco %d phan tu lon nhat", demmax(a,n));
	printf("\nvi tri phan tu max dau tien: %d", inmax(a,n));
	printf("\nnhap phan tu can them: ");
	scanf("%d", &x);
	printf("\nmang sau khi them dau: ");s
	themdau(a,n,x);
	printf("\nmang sau khi them cuoi: ");
	themcuoi(a,n,x);
	printf("\nnhap vao vi tri can them %d: ", x);
	
	scanf("%d", &k);
	printf("\nmang sau khi them %d vao vi tri %d: ", x, k);
	themvt(a,n,x,k);
	printf("\nmang sau khi xoa phan tu chan dau: ");
	xoachandau(a,n);
	printf("\nmang sau khi xoa phan tu max: ");
	xoaallmax(a,n);
	printf("\nmang sau khi sap tang: ");
	sapxep(a,n);
	xuatmang(a,n);
	
}

