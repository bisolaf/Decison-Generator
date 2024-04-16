package sol;

import java.util.List;
import java.util.Objects;

import src.ITreeNode;
import src.Row;

/**
 * A class representing an inner node in the decision tree.
 */
public class AttributeNode implements ITreeNode {
    // TODO: add more fields as needed
    private String attributeType;
    private String defaultAttribute; //attribute value
    private List<ValueEdge> edgeList;

    /**
     * Constructor for Attribute Node
     * @param attributeType - String of Attribute (Heading)
     * @param defaultAttribute - Default attribute value of node
     * @param edgeList - List of Value Edges for Node
     */
    public AttributeNode(String attributeType, String defaultAttribute, List<ValueEdge> edgeList) {
        this.attributeType = attributeType;
        this.defaultAttribute = defaultAttribute;
        this.edgeList = edgeList;
    }

    /**
     *Method to return GetDecision
     * @param forDatum - returns the attribute value if node has nothing to split on or runs recursively till it reaches a leaf
     * Returns a string
     */
    @Override
    public String getDecision(Row forDatum) {
        for (ValueEdge e : this.edgeList) {
            if (Objects.equals(e.getEdgeValue(), forDatum.getAttributeValue(this.attributeType))) {
                ITreeNode finder = e.getChild();
                return finder.getDecision(forDatum);
            }
        }
            return this.defaultAttribute ;
        }
    }



