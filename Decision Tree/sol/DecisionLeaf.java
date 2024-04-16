package sol;

import src.ITreeNode;
import src.Row;

/**
 * A class representing a leaf in the decision tree.
 */
public class DecisionLeaf implements ITreeNode  {
    // TODO: add fields as needed
    private String outcome;


    /**
     * Setter to set decision leaf outcome
     * @param outcome
     */
    public DecisionLeaf(String outcome) {
        this.outcome = outcome;
    }

    /**
     * Setter for getDecision
     * @param forDatum
     */
    public String getDecision(Row forDatum) {
        return this.outcome;
    }

}
