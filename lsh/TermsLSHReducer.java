import java.util.Scanner;
import java.io.*;

class TermsLSHReducer {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String lineValue = null;
		String[] temp = null;
		String currentCatID = null;
		String catID = null;
		String filename = null;
		BufferedWriter writer = null;
		try{
			while (sc.hasNextLine()){
				lineValue = sc.nextLine();
				temp = lineValue.split("\t");
				if (temp.length >= 2){
					catID = temp[0];
					if (catID != currentCatID){
						currentCatID = catID;
						//output item with same category in one file
						//e.g. cat_id.txt 
						//101.txt (if cat_id = 101)
						filename = currentCatID + ".txt";
						if (writer != null)
							writer.close();
						writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename, true), "utf-8"));
					}
					writer.write(temp[1]);
					writer.newLine();
				}
			}
		} catch (IOException ex){
			//report
		}
	}
	
}