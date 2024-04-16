package sol;

import src.ITreeGenerator;
import src.ITreeNode;
import src.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that implements the ITreeGenerator interface used to generate a decision tree
 */
public class TreeGenerator  implements ITreeGenerator<Dataset>  {
    // TODO: document this field
    private Dataset training;
    private String targetatt;
    private List<String> attr;
    private ITreeNode root;

    /**
     * A constructor for TreeGenerator
     */
    public TreeGenerator() {

    }
    /**
     * A method that creates the tree for a specific dataset and a target attribute
     * @param trainingSet - Dataset to generate tree for
     * @param targetAttribute  - attribute to generate tree on
     */
    public void generateTree(Dataset trainingSet, String targetAttribute) {
        if (trainingSet.size() == 0) {
            throw new RuntimeException("Dataset empty");
        } else {
            List<String> copy = new ArrayList<>(trainingSet.getAttributeList());
            copy.remove(targetAttribute);
            List<Row> copy1 = new ArrayList<>(trainingSet.getDataObjects());
            Dataset check = new Dataset(copy, copy1, trainingSet.getSelectionType());
            this.root = this.generateTreeHelper(check, targetAttribute);
        }

    }

    /**
     * A method helper for generateTree by building the nodes from bottom up
     * @param data - Dataset to generate tree for
     * @param targetAttribute  - attribute to generate tree on
     * This method returns an ITreeNode which is called in generateTree that connects the tree
     */

    private ITreeNode generateTreeHelper(Dataset data, String targetAttribute) {
        String defaultAttribute = data.getDefault(targetAttribute);
        if ((data.isAttributeListEmpty() || data.isSameAttribute(targetAttribute)) ){ // makes leaf
            //return new DecisionLeaf(data.getDefault(targetAttribute));
            return new DecisionLeaf(defaultAttribute);
        }
        else {
            String nextAttribute = data.getAttributeToSplitOn();
            List<ValueEdge> edges = new ArrayList<>();
            List<Dataset> partitionedList = data.splitData(nextAttribute);
            for (Dataset subset: partitionedList) {
                ITreeNode subtree = this.generateTreeHelper(subset, targetAttribute); //nextAttribute);
                ValueEdge branch = new ValueEdge(subset.getValue(nextAttribute), subtree);
                edges.add(branch);
            }
            return new AttributeNode(nextAttribute, defaultAttribute, edges);
        }
    }


    /**
     * A method that gets the decsion for the inputted row by going through the Nodes in the tree
     * @param r - row to get the decison for
     * This returns the string of the target attribute
     */
    @Override
    public String getDecision(Row r){
        return this.root.getDecision(r);
    }

}
