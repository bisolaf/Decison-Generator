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
    // IMPORTANT: for this filepath to work, make sure the project is open as the top-level directory in IntelliJ
    // (See the first yellow information box in the handout testing section for details)
   // String trainingPath = "data/testgame.csv"; // TODO: replace with your own input file
   // String targetAttribute = "outcome"; // TODO: replace with your own target attribute
   // TreeGenerator testGenerator;


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
        // builds a TreeGenerator object and generates a tree for "foodType"
        this.testGenerator = new TreeGenerator();
//        TODO: Uncomment this once you've implemented generateTree
//        this.testGenerator.generateTree(training, this.targetAttribute);
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
        // TODO: make your own assertions based on the expected classifications
        // TODO: Uncomment this once you've implemented getDecision
        // Assert.assertEquals("buy", testGenerator.getDecision(Game7));
    }
}
