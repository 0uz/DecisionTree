public class Cancer {
    int[] cancer= new int[10];
    boolean isMalignant;

    public Cancer(int[] cancer) {
        for (int i=0;i<10;i++){
            this.cancer[i]=cancer[i];
        }
        isMalignant = cancer[10] != 4;
    }

}
