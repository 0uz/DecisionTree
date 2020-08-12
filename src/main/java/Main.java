import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {

        try {
            BufferedReader inputFile = new BufferedReader(new FileReader("src/main/resources/breast-cancer-wisconsin.data"));

            ArrayList<Cancer> list = new ArrayList<Cancer>();
            StringTokenizer st;

            Cancer cancer;
            String line;
            int[] data = new int[11];

            while ((line = inputFile.readLine()) != null){
                int counter = 0;
                boolean isValid=true;

                st = new StringTokenizer(line,",");
                while (st.hasMoreTokens()){
                    String token = st.nextToken();
                    if (!token.equals("?")){
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

            for(Cancer C : list){
                for (int i : C.cancer){
                    System.out.print(i+"\t");
                }
                System.out.println(C.isMalignant);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
