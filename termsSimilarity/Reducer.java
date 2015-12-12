import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Reducer {

    public static final String fileRoot = ".";
    public static final String inputFileDir = fileRoot + "/input/hashValues";
    public static final String outputFileDir = fileRoot + "/result";

    public static void main(String[] args) {

        String outputFileName = outputFileDir + "/result.txt";
        deleteFile(outputFileName);

        Scanner sc = new Scanner(System.in);
        String lineValue = null;
        String[] temp = null;
        while (sc.hasNextLine()){
            lineValue = sc.nextLine();
            temp = lineValue.split(" ");
            if (temp.length >= 2){
                long comparedItemId = Long.valueOf(temp[0]);
                long itemId = Long.valueOf(temp[1]);
                int catId = Integer.valueOf(temp[2]);
                int[] hashArray = getHashArrayForItem(comparedItemId, catId);
                int[] comparedHashArray = getHashArrayForItem(itemId, catId);

                if (itemId == comparedItemId) {
                    continue;
                } else {
                    int similarCount = 0;
                    int hashArrayLength = comparedHashArray.length;
                    for (int i = 0; i < hashArrayLength; i++) {
                        int comparedHashVal = comparedHashArray[i];
                        int hashVal = hashArray[i];
                        if (hashVal == comparedHashVal) {
                            similarCount ++;
                        }
                    }

                    float similarity = 1.f * similarCount / hashArrayLength;

                    String outputString = String.format("%d,%d %.3f", comparedItemId, itemId, similarity);
                    appendToFile(outputFileName, outputString + "\n");
                }
            }
        }
    }

    private static int[] getHashArrayForItem(long itemId, int catId) {

        String fileName = inputFileDir + "/" + catId + ".txt";
        int[]  hashArray = findHashArray(fileName, itemId);
        return hashArray;
    }


    private static int[] findHashArray(String fileName, long destItemId) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String lineString = null;
            while ((lineString = reader.readLine()) != null) {
                long itemId = getItemIdFromLine(lineString);
                int[] hashArray = getHashArrayFromLine(lineString);
                if (itemId == destItemId) {
                    return hashArray;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return null;
    }

    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    private static void appendToFile(String outputFileName, String outputString) {
        try {

            String content = outputString; //"This is the content to write into file";

            File file = new File(outputFileName);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(content);
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] getHashArrayFromLine(String lineString) {

        String[] splitStrings = lineString.split(":");
        String hashString = splitStrings[1];
        hashString = hashString.replace(" ", "");

        String keyString = hashString.substring(1, hashString.length()-1);
        String[] keyStrings = keyString.split(",");
        int arrayLength = keyStrings.length;
        int[] hashArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            String hashValueString = keyStrings[i];
            hashArray[i] = Integer.valueOf(hashValueString);
        }

        return hashArray;
    }

    private static long getItemIdFromLine(String lineString) {
        String[] splitStrings = lineString.split(":");
        String itemIdString = splitStrings[0];
        long itemId = Long.valueOf(itemIdString);
        return itemId;
    }
}
