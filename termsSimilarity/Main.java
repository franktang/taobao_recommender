import java.io.*;
import java.util.ArrayList;

public class Main {

    public static final String fileRoot = "/Users/ftuser/Desktop/Vivien/temp/bigData/src";
    public static final String inputFileDir = fileRoot + "/input/hashValues";
    public static final String outputFileDir = fileRoot + "/result";

    public static final String dataSetDir = fileRoot + "/input/dataset";

    private static ArrayList<Long> testItemSet;
//    private static String fileName, outputFileName;
//    public static final int HAS_ARRAY_LENGTH = 2;

    public static void main(String[] args) {


        deleteFile(outputFileDir + "/result.txt");
        testItemSet = readTestItems(dataSetDir + "/test_items_new.txt");

        final File folder = new File(inputFileDir);
        processFilesForFolder(folder);


//        analyseItemsByLines(dataSetDir + "/dim_items.txt");

//        fileName = inputFileDir + "/cat_01.txt";
//        outputFileName = outputFileDir + "/cat_01.txt";
//        processFileByLines(fileName);
    }

    private static ArrayList<Long> readTestItems(String fileName) {
        ArrayList<Long> testItemList = new ArrayList<Long>();

        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String lineString = null;
            int line = 1;
            //一次读一行，读入null时文件结束
            while ((lineString = reader.readLine()) != null) {
//                System.out.println(lineString);
                testItemList.add(Long.valueOf(lineString));
                line++;
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

//    private static void analyseItemsByLines(String itemsFile) {
//        File file = new File(itemsFile);
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new FileReader(file));
//            String lineString = null;
//            int line = 1;
//            //一次读一行，读入null时文件结束
//            while ((lineString = reader.readLine()) != null) {
////                System.out.println(lineString);
//                String[] rowArray = lineString.split(" ");
//                long itemId = Long.valueOf(rowArray[0]);
//                long catId = Long.valueOf(rowArray[1]);
//
//                if (testItemSet.contains(itemId)) {
//                    compareItem(itemId, catId);
//                }
//
//                //terms
////                String termsArrayString = rowArray[2];
////                int[] termsArray = stringToArray(termsArrayString);
////                System.out.print(String.format("item : %d, catId : %d", itemId, catId));
////                for (int i = 0; i < termsArray.length; i++) {
////                    int term = termsArray[i];
////                    System.out.print(String.format("%d,", term));
////                }
////                System.out.println();
//                line++;
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e1) {
//                }
//            }
//        }
//    }

//    private static void compareItem(long itemId, long catId) {
////        String fileName = inputFileDir + "/cat_" + catId + ".txt";
//        String fileName = inputFileDir + "/" + catId + ".txt";
//        int[]  hashArray = findHashArray(fileName, itemId);
//        compareWithOtherItems(itemId, catId, hashArray, fileName);
//    }

    private static int[] findHashArray(String fileName, long destItemId) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String lineString = null;
            int line = 1;
            while ((lineString = reader.readLine()) != null) {
                long itemId = getItemIdFromLine(lineString);
                int[] hashArray = getHashArrayFromLine(lineString);
                if (itemId == destItemId) {
                    return hashArray;
                }

                line++;
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

    public static void compareWithOtherItems(long comparedItemId, long catId, int[] comparedHashArray, String fileName) {
        String outputFileName = outputFileDir + "/result.txt";
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String lineString = null;
            int line = 1;
            //一次读一行，读入null时文件结束
            while ((lineString = reader.readLine()) != null) {
//                System.out.println(lineString);
                long itemId = getItemIdFromLine(lineString);
                int[] hashArray = getHashArrayFromLine(lineString);

                if (itemId == comparedItemId || testItemSet.contains(itemId)) {
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

                    String outputString = String.format("%d,%d %.1f", comparedItemId, itemId, similarity);
//                    System.out.println(String.format("similarity of item %d and item %d is %.2f", comparedItemId, itemId, similarity));
                    appendToFile(outputFileName, outputString + "\n");
                }

                line++;
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

//            System.out.print(outputString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processFileByLines(long catId, String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String lineString = null;
            int line = 1;
            //一次读一行，读入null时文件结束
            while ((lineString = reader.readLine()) != null) {
//                System.out.println(lineString);
                long itemId = getItemIdFromLine(lineString);
                if (!testItemSet.contains(itemId)) {
                    continue;
                }
                int[] hashArray = getHashArrayFromLine(lineString);
                compareWithOtherItems(itemId, catId, hashArray, fileName);

                line++;
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



    private static int[] stringToArray(String arrayString) {
        String[] keyStrings = arrayString.split(",");
        int arrayLength = keyStrings.length;
        int[] hashArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            String hashValueString = keyStrings[i];
            hashArray[i] = Integer.valueOf(hashValueString);
        }

        return hashArray;
    }


    private static int[] getHashArrayFromLine(String lineString) {

        String[] splitStrings = lineString.split(":");
        String hashString = splitStrings[1];//"[7,9]"
        hashString = hashString.replace(" ", "");
        //"[1,2]" --> int[]

        String keyString = hashString.substring(1, hashString.length()-1);
        String[] keyStrings = keyString.split(",");
        int arrayLength = keyStrings.length;
        int[] hashArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            String hashValueString = keyStrings[i];
            hashArray[i] = Integer.valueOf(hashValueString);
        }

        //print for testing
//                for (int i = 0; i < arrayLength; i++) {
//                    System.out.println(hashArray[i]);
//                }

        return hashArray;
    }

    private static long getItemIdFromLine(String lineString) {
        String[] splitStrings = lineString.split(":");
        String itemIdString = splitStrings[0];
        long itemId = Long.valueOf(itemIdString);
        return itemId;
    }

    public static void processFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                processFilesForFolder(fileEntry);
            } else {
                String fileName = fileEntry.getName();
                if (!fileName.endsWith(".txt")) {
                    continue;
                }
                long catId = Long.valueOf(fileName.replace(".txt", ""));
                System.out.println("--- processing catId : " + catId);
                String fileCompleteName = inputFileDir + "/" + fileName;

                processFileByLines(catId, fileCompleteName);
            }
        }
    }

}
