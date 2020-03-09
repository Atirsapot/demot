package demo.d9;

public class PoistaTaulukosta {
	
	/**
     * Tulostetaan taulukosta lkm kappaletta lukuja
     * @param t   käsiteltävä taulukko
     * @param lkm käsitelteltävien alkioiden lkm
     * <pre name="test">
     * lkm = poista(t,lkm,9) === 6;
     * lkm = poista(t,lkm,2) === 5;  
     * lkm = poista(t,lkm,4) === 5; 
     * </pre>
     */
	public static int poista (int t[], int lkm, int n) {
		for (int i = 0; i < t.length; i++) {
			if(t[i]== n) {
				lkm--;
				for (int j=i; j <= t.length-i ;j++) {
					t[j] = t[j+1];
					
				}
			}
			
		}
		return lkm;
		
		
	}
    /**
     * Tulostetaan taulukosta lkm kappaletta lukuja
     * @param t   käsiteltävä taulukko
     * @param lkm käsitelteltävien alkioiden lkm
     */
    public static void tulosta(int t[], int lkm) {
        int tlkm = lkm;
        if (tlkm > t.length) tlkm = t.length;
        for (int i = 0; i < tlkm; i++)
            System.out.print(t[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int t[] = { 4, 7, 6, 3, 6, 2 };
        int lkm = 6;

        lkm = poista(t, lkm, 6); /* => t = {4,7,3,2}, lkm = 4 */
        tulosta(t, lkm);
    }
}