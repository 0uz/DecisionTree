import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {

        try {
            BufferedReader inputFile = new BufferedReader(new FileReader("src/main/resources/breast-cancer-wisconsin.data"));

            ArrayList<Cancer> list = new ArrayList<>();
            StringTokenizer st; // Separating data that read from file

            Cancer cancer;
            String line;
            int[] data = new int[11];

            while ((line = inputFile.readLine()) != null){
                int counter = 0;
                boolean isValid=true;

                st = new StringTokenizer(line,",");
                while (st.hasMoreTokens()){
                    String token = st.nextToken();
                    if (!token.equals("?")){ //extract invalid data which include ?
                        data[counter]=Integer.parseInt(token);
                        counter++;
                    }else{
                        isValid=false;
                    }
                }
                if (isValid){
                    cancer =  new Cancer(data);
                    list.add(cancer);
                }
            }
            inputFile.close();
          /*   Collections.shuffle(list);
            ArrayList<Cancer> trainList = new ArrayList<>();
            ArrayList<Cancer> testList = new ArrayList<>();

            for (int i =0;i<list.size();i+=2){
                trainList.add(list.get(i));
                testList.add(list.get(++i));
            }
           System.out.println("----------------TRAIN LIST-----------------");
            printList(trainList);
            System.out.println("----------------TEST LIST-----------------");
            printList(testList);*/


            ArrayList<Cancer> smallPartReturn = new ArrayList<>();
            ArrayList<Cancer> bigPartReturn = new ArrayList<>();
            DecisionTree d = new DecisionTree(list);
            d.separateList(list,1,3,smallPartReturn,bigPartReturn);
            int[] arr = d.separateListAndCount(list,1,3);
            int[] arr2 = d.countList(list);

            System.out.println("--------------SMALL-----------------");
            printList(smallPartReturn);
            System.out.println("--------------BIG-----------------");
            printList(bigPartReturn);

            System.out.println("Total Malignant : "+ arr2[0]+" Total Benign : "+ arr2[1]);
            System.out.println("Small Part Malignant count : "+arr[0]);
            System.out.println("Small Part Benign count : "+arr[1]);
            System.out.println("Big Part Malignant count : "+arr[2]);
            System.out.println("Big Part Benign count : "+arr[3]);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    static void printList(ArrayList<Cancer> list){
        for(Cancer C : list){
            for (int i : C.cancer){
                System.out.print(i+"\t");
            }
            System.out.println(C.isMalignant);

        }
    }
}
