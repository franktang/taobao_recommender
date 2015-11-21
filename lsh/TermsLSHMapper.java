import info.debatty.java.lsh.LSHMinHash;
import java.util.Scanner;
import java.util.Arrays;

class TermsLSHMapper {
	public static final int VECTOR_SIZE = 219178 + 1;
	
	public static final int BAND_SIZE = 2;
	
	public static final int BUCKET_SIZE = 10;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String lineValue = null;
		String[] temp = null;
		String[] termsArray = null;
		boolean[] vector = null;
		
		// Create and configure LSH algorithm
		LSHMinHash lsh = new LSHMinHash(BAND_SIZE, BUCKET_SIZE, VECTOR_SIZE);
		
		while (sc.hasNextLine()){
			
			//initialize the boolean vector for each item
			vector = new boolean[VECTOR_SIZE];
			lineValue = sc.nextLine();
			temp = lineValue.split(" ");
			//temp[0]: item id
			//temp[1]: cat id
			//temp[2]: terms
			if (temp.length >= 3){
				termsArray = temp[2].split(",");
				if (null != termsArray && termsArray.length > 0){
					for (int i=0; i<termsArray.length; i++){
						int termsValue = Integer.parseInt(termsArray[i]);
						if (termsValue>=0 && termsValue<VECTOR_SIZE){
							vector[termsValue] = true;
						}
					}
				}
				//Perform hash
				int[] hash = lsh.hash(vector);
				//Print out format is: "cat_id	item_id:[lsh_hash]"
				System.out.println(temp[1] + "\t" + temp[0] + ":" + Arrays.toString(hash));
			}
			
			
		}
	}
	
	static void print(int[] array) {
		System.out.print("[");
		for (int v : array) {
			System.out.print("" + v + ",");
		}
		System.out.print("]");
		System.out.print("\n");
	}
	
}