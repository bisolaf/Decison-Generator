package sol;

import src.ITreeNode;

/**
 * A class that represents the edge of an attribute node in the decision tree
 */
public class ValueEdge {
    // TODO: add more fields if needed
    private String value;
    private ITreeNode child;

    /**
     * Constructor for ValueEdge Class
     * @param value - String content of edge case
     * @param child - Node that is split into
     */
    public ValueEdge(String value, ITreeNode child) {
        this.value = value;
        this.child = child;
    }


    /**
     * Method that gets this.Child
     */
    public ITreeNode getChild() {
        return this.child;
    }

    /**
     * Method that gets this.value
     */
    public String getEdgeValue() {
        return this.value;
    }
}
