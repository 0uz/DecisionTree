import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.StreamSupport;

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
            Collections.shuffle(list);
            ArrayList<Cancer> trainList = new ArrayList<>();
            ArrayList<Cancer> testList = new ArrayList<>();

            for (int i =0;i<list.size();i+=2){
                trainList.add(list.get(i));
                testList.add(list.get(++i));
            }
            System.out.println("----------------TRAIN LIST-----------------");
            printList(trainList);
            System.out.println("----------------TEST LIST-----------------");
            printList(testList);

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
