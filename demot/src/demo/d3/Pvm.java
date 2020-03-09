package demo.d3;
import fi.jyu.mit.ohj2.Mjonot;


public class Pvm
{

int pv = 0;
int kk = 0;
int v = 0;

	
public static void main(String[] args) {
       Pvm p1 = new Pvm(21, 1,  2015);
       Pvm p2 = new Pvm(12, 12, 2012);
       Pvm p3 = new Pvm(19, 8,  1990);
       System.out.println(p1);
       System.out.println(p2);
       System.out.println(p3);
   }

   public Pvm(int pv, int kk, int v) {
	   
	   this.pv = pv;
	   this.kk = kk;
	   this.v = v;
   }

   @Override
   public String toString() {
	   return String.format("%02d.%02d.%02d", pv,kk, v);
   }
   
}


