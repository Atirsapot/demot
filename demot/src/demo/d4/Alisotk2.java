package demo.d4;
 public class Alisotk2 {

  private static int i(StringBuffer s) {
    return Integer.parseInt(s.toString());
  }
  private static void set(StringBuffer s,int i) {
    s.delete(0, s.length()).append(""+i);
  }

 private static StringBuffer b; private static int c;

 private static void s_1(StringBuffer a, int b)
{
	 	   int d;
	 	   d  = i(a);
	 	   
	 	   c  = b + 3;
	 	   
	 	   b  = d - 1;
	 	  
	 	   set(a,c - 5);
	 	   
}
/* 11 */
/* 12 */ private static void a_2(int a, StringBuffer b)
/* 13 */ {
		 
/* 14 */   c  = a + i(b); 
/* 15 */   { int c; c = i(b);

/* 16 */   a = 8 * c; }

/* 17 */   set(b,175);

/* 18 */ }
/* 19 */
/* 20 */ public static void main(String[] args) {
/* 21 */   StringBuffer a = new StringBuffer("4"); int d=9;
/* 22 */   System.out.println("" + a + " " + b + " " + c + " " + d);
/* 23 */   b=new StringBuffer("3"); c=2; d=1;
/* 24 */   s_1(b,c);
/* 25 */   a_2(d,a);
System.out.println(b);
/* 26 */   s_1(a,3+d);
/* 27 */   System.out.println("" + a + " " + b + " " + c + " " + d);
/* 28 */ }
}