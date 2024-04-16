package sol;

import java.util.*;
import java.util.Random;

import src.AttributeSelection;
import src.IDataset;
import src.Row;

/**
 * A class representing a training dataset for the decision tree
 */
public class Dataset  implements IDataset {
    private List<String> attributeList;
    private List<Row> dataObjects;
    private AttributeSelection selectionType;

    /**
     * Constructor for a Dataset object
     *
     * @param attributeList      - a list of attributes
     * @param dataObjects        -  a list of rows
     * @param attributeSelection - an enum for which way to select attributes
     **/
    public Dataset(List<String> attributeList, List<Row> dataObjects, AttributeSelection attributeSelection) {

        this.attributeList = attributeList;
        this.dataObjects = dataObjects;
        this.selectionType = attributeSelection;
    }

    /**
     * Size Method
     * returns the size of a list of rows
     **/
    @Override
    public int size() {
        return this.dataObjects.size();
    }

    /**
     * Method to get default target of each dataset
     *
     * @param targetatt - attribute to get default value of
     *                  returns a string
     **/
    public String getDefault(String targetatt) {
        List<Dataset> test = this.splitData(targetatt);
        int counter = 0;
        String defaultValue = null;
        for (Dataset d : test) {
            if (d.size() > counter) {
                counter = d.size();
                defaultValue = d.getValue(targetatt);
            }
        }
        return defaultValue;
    }

    /**
     * Method to remove attribute from a list
     *
     * @param att      - attribute to be removed
     *Returns a list of string with removed attribute
    /**
     * Helper for Split data
     *
     * @param attr attribute to check
     *             Returns list of strings of rows with that specific attribute
     **/
    private List<String> getAllValues(String attr) {
        List<String> stringlist = new ArrayList<>();
        String checkloc;
        for (Row row : this.dataObjects) {
            checkloc = row.getAttributeValue(attr);
            if (!stringlist.contains(checkloc)) {
                stringlist.add(checkloc);
            }
        }
        return stringlist;
    }

    /**
     * Helper to get first row attribute value
     *
     * @param init - attribute type
     *             Returns string of the value
     **/
    public String getValue(String init) {
        return this.dataObjects.get(0).getAttributeValue(init);
    }

    /**
     * Method that partitions the dataset on a specific attribute
     *
     * @param attribute - attribute to split on
     *                  Returns dataset which is like a subset of possible values in that attribute
     **/
    public List<Dataset> splitData(String attribute) {
        List<Dataset> subset = new ArrayList<>();
        List<String> lesslist = new ArrayList<>(this.attributeList);
        lesslist.remove(attribute);
        for (String st : this.getAllValues(attribute)) {
            List<Row> data = new ArrayList<>();
            for (Row r : this.dataObjects) {
                if (r.getAttributeValue(attribute).equals(st)) {
                    ;
                    data.add(r);
                }
            }
            Dataset fin = new Dataset(lesslist, data, this.selectionType);
            subset.add(fin);
        }
        return subset;
    }

    /**
     * Method that checks if attribute values of all the rows are the same for an input attribute type
     *
     * @param attributeValue - String of the attribute type to check
     *                       Returns Boolean
     **/
    public boolean isSameAttribute(String attributeValue) {
        String value = this.dataObjects.get(0).getAttributeValue(attributeValue);
        for (Row r : this.dataObjects) {
            if (!r.getAttributeValue(attributeValue).equals(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that determines whether there are exists at least one type of attributes in the data set
     *
     * @return Boolean indicating if attributeList is empty
     */
    public boolean isAttributeListEmpty() {
        return (this.attributeList.isEmpty());
    }

    /**
     * Method that creates a random search criteria
     */
    private int getRandom() {
        Random random = new Random();
        int rand = 0;
        int rando = this.attributeList.size();

        rand = random.nextInt(rando);  }

        return rand;
    }

    /**
     * Method that gets Attribute List
     */
    public List<String> getAttributeList() {
        return this.attributeList;
    }


    /**
     * Null Method to get DataObjects
     */
    @Override
    public List<Row> getDataObjects() {
        return this.dataObjects;
    }

    /**
     * Null Method to get Attribute Selection
     */
    @Override
    public AttributeSelection getSelectionType() {
        return this.selectionType;
    }

    /**
     * Method that gets the criteria to split attribytes on
     */
    public String getAttributeToSplitOn() {
    switch (this.selectionType) {
        case ASCENDING_ALPHABETICAL -> {  // Change AttributeSelection.ASCENDING_ALPHABETICAL => ASCENDING_ALPHABETICAL
            return this.attributeList.stream().sorted().toList().get(0);
        }
        case DESCENDING_ALPHABETICAL -> { // Change AttributeSelection.DESCENDING_ALPHABETICAL => DESCENDING_ALPHABETICAL
            return this.attributeList.stream().sorted().toList().get(this.attributeList.size() - 1);
        }
        case RANDOM -> {   // Change AttributeSelection.RANDOM => Random

             return this.attributeList.get(this.getRandom());

        }
    }
    throw new RuntimeException("Non-Exhaustive Switch Case");
    }
}
