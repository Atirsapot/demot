package demo.d9;

public class quicksort {

	public static void main(String[] args) {
		
		int[] arr = {14, 6, 10, 22, 4, 17, 25, 13};
		bubbleSort(arr);
		System.out.println();
		insertionSort(arr);
		
	}
	
	public static void bubbleSort(int[] arr) 
    { 
		
        int n = arr.length; 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (arr[j] > arr[j+1]) 
                { 
                    // swap arr[j+1] and arr[i] 
                    int temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                } 
        for(int i = 0;i<arr.length; i++) {
        	if(i == arr.length-1)
        		System.out.println(arr[arr.length-1]);
        	else
        	System.out.print(arr[i]+", ");
        }
    }
	public static void insertionSort(int arr[]) 
    { 
        int n = arr.length; 
        for (int i=1; i<n; ++i) 
        { 
            int key = arr[i]; 
            int j = i-1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j>=0 && arr[j] > key) 
            { 
                arr[j+1] = arr[j]; 
                j = j-1; 
            } 
            arr[j+1] = key; 
        } 
        for(int i = 0;i<arr.length; i++) {
        	if(i == arr.length-1)
        		System.out.println(arr[arr.length-1]);
        	else
        	System.out.print(arr[i]+", ");
        }
    }
		
	
}
