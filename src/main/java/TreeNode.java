import java.util.ArrayList;

public class TreeNode {
    TreeNode smallPartNode;
    TreeNode bigPartNode;

    int cancerDataIndex;
    int separatePoint;

    ArrayList<Cancer> list;

    public TreeNode(ArrayList<Cancer> list) {
        this.list = list;
    }
}
