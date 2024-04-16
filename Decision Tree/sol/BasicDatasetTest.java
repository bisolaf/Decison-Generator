package sol;

import org.junit.Assert;
import org.junit.Test;
import src.AttributeSelection;
import src.DecisionTreeCSVParser;
import src.Row;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

/**
 * A class to test basic decision tree functionality on a basic training dataset
 */
public class BasicDatasetTest {

    /**
     /**
     /**
     * Constructs the decision tree for testing based on the input file and the target attribute.
     */
    @Before
    public void buildTreeForTest() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);
        this.testGenerator = new TreeGenerator();
    }

    /**
     * Tests the expected classification of the "Game7" row is "buy"
     */
    @Test
    public void testClassification() {
        Row Game7 = new Row("test row (Game3)");// makes a new (partial) Row representing the tangerine from the example
        // TODO: make your own rows based on your dataset
        Game7.setAttributeValue("Price", "50");
        Game7.setAttributeValue("Has songs", "No");
        Game7.setAttributeValue("Has guns", "No");
    
    }
}
