import java.util.ArrayList;

public class DecisionTree {
    TreeNode root;
    boolean[] availableDataIndex;
    public DecisionTree(ArrayList<Cancer> list) {
        root =  new TreeNode(list);
        availableDataIndex = new boolean[10];
        for (int i=0;i<10;i++){
            availableDataIndex[i]=true;
        }
        availableDataIndex[0]=false;
    }

    public void separateList(ArrayList<Cancer> list, int index, int separatePoint,
                             ArrayList<Cancer> smallPart, ArrayList<Cancer> bigPart){
         if (index >=10 || index < 0) {
             System.out.println("Index Yanlis");
             return;
         }

         for (Cancer c : list){
             if(c.cancer[index] <= separatePoint){
                 smallPart.add(c);
             }else{
                 bigPart.add(c);
             }
         }

    }

    public int[] separateListAndCount(ArrayList<Cancer> list, int index, int separatePoint){
        int malignantSmallPart=0;
        int benignSmallPart=0;
        int malignantBigPart=0;
        int benignBigPart=0;

        if (index >=10 || index < 0) {
            System.out.println("Index Yanlis");
            return null;
        }

        for (Cancer c : list){
            if(c.cancer[index] <= separatePoint){
                if (c.isMalignant){
                    malignantSmallPart++;
                }else{
                    benignSmallPart++;
                }
            }else{
                if (c.isMalignant){
                    malignantBigPart++;
                }else{
                    benignBigPart++;
                }
            }
        }
        return new int[]{malignantSmallPart,benignSmallPart,malignantBigPart,benignBigPart};
    }

    public int[] countList(ArrayList<Cancer> list){
        int malignant = 0;
        int benign = 0;
        for (Cancer c : list){
            if (c.isMalignant){
                malignant++;
            }else{
                benign++;
            }
        }
        return new int[]{malignant,benign};

    }

    public int findBestSeparatePoint(ArrayList<Cancer> list, int index){
        int bestSeparatePoint=1;
        int[] numbers =  separateListAndCount(list,index,bestSeparatePoint);
        float bestEntropy = calculateEntropy(numbers);
        float tempEntropy;
        for (int i=2;i<=10;i++){
            numbers =  separateListAndCount(list,index,i);
            tempEntropy = calculateEntropy(numbers);
            if (tempEntropy < bestEntropy){
                bestEntropy = tempEntropy;
                bestSeparatePoint = i;
            }
        }
        return bestSeparatePoint;
    }

    public float calculateEntropy(int[] number){

        return 0f;
    }


}
