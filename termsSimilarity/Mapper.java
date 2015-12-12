import java.io.*;
import java.util.ArrayList;

public class Mapper {

    public static final String fileRoot = ".";
    public static final String inputFileDir = fileRoot + "/input/hashValues";
    public static final String outputFileDir = fileRoot + "/result";

    public static final String dataSetDir = fileRoot + "/input/dataset";

    private static ArrayList<Long> testItemSet;

    public static void main(String[] args) {

        testItemSet = readTestItems(dataSetDir + "/test_items_new.txt");
        outputItemPairs(dataSetDir + "/dim_items.txt");
    }

    private static ArrayList<Long> readTestItems(String fileName) {
        ArrayList<Long> testItemList = new ArrayList<Long>();

        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String lineString = null;

            while ((lineString = reader.readLine()) != null) {
                testItemList.add(Long.valueOf(lineString));
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

        return testItemList;
    }

    private static void outputItemPairs(String itemsFile) {
        File file = new File(itemsFile);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String lineString = null;

            while ((lineString = reader.readLine()) != null) {

                String[] rowArray = lineString.split(" ");
                long itemId = Long.valueOf(rowArray[0]);
                long catId = Long.valueOf(rowArray[1]);

                if (testItemSet.contains(itemId)) {
                    outputItemInCategory(itemId, catId);
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
    }

    private static void outputItemInCategory(long comparedItemId, long catId) {
        String fileName = inputFileDir + "/" + catId + ".txt";
        String outputFileName = outputFileDir + "/result.txt";
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String lineString = null;
            while ((lineString = reader.readLine()) != null) {
//                System.out.println(lineString);
                long itemId = getItemIdFromLine(lineString);
                if (itemId == comparedItemId) {
                    continue;
                } else {
                    String outputString = String.format("%d %d %d", comparedItemId, itemId, catId);
                    System.out.println(outputString);
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
    }

    private static long getItemIdFromLine(String lineString) {
        String[] splitStrings = lineString.split(":");
        String itemIdString = splitStrings[0];
        long itemId = Long.valueOf(itemIdString);
        return itemId;
    }
}
