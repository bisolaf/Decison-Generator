package sol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertFalse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import src.AttributeSelection;
import src.DecisionTreeCSVParser;
import src.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * A class containing the tests for methods in the TreeGenerator and Dataset classes
 */
public class DecisionTreeTest {
   private String trainingPathGame = "data/testgame.csv"; // TODO: replace with your own input file
    private String trainingPathFruit = "data/fruits-and-vegetables.csv"; // TODO: replace with your own input file
    private String trainingPathWeather = "data/weather.csv"; // TODO: replace with your own input file
    private String trainingPathempty = "data/empty.csv";

    private String trainingPathemptyh = "data/emptyheading.csv";
    private String trainingPathcountry = "data/country.csv";
    private String targetAttributeGame = "outcome"; // TODO: replace with your own target attribute
    private String targetAttributeWeather = "event"; // TODO: replace with your own target attribute
    private String targetAttributeFruit = "foodType"; // TODO: replace with your own target attribute
    private String targetAttributeempty = "";
    private String targetAttributeemptyH = "Y";

    private String targetAttributcountry = "climate";

    private TreeGenerator testGenerator;
    private TreeGenerator checkGame;
    private TreeGenerator checkFruit;
    private Dataset trainingWeather;
    private Dataset trainingGame1;
    private Dataset trainingFruit;
    private Dataset empty;
    private Dataset emptyh;
    private Dataset country;
    private TreeGenerator trainingempty;
    private TreeGenerator trainingemptyh;
    private TreeGenerator trainingcountry;
    private Dataset emptydata;
    private String pathSplitDataTest = "data/splitdata.csv";
    private String pathSplitDataA = "data/splitdata-alphabet-a.csv";
    private String pathSplitDataB = "data/splitdata-alphabet-b.csv";

    private String pathSplitDataC;
    private Dataset splitDataTest;
    private Dataset splitDataA;
    private Dataset splitDataB;
    private Dataset splitDataC ;


    //TODO: Write more unit and system tests! Some basic guidelines that we will be looking for:
    // 1. Small unit tests on the Dataset class testing the IDataset methods
    // 2. Small unit tests on the TreeGenerator class that test the ITreeGenerator methods
    // 3. Tests on your own small dataset (expect 70% accuracy on testing data, 95% on training data)
    // 4. Test on the villains dataset (expect 70% accuracy on testing data, 95% on training data)
    // 5. Tests on the mushrooms dataset (expect 70% accuracy on testing data, 95% on training data)
    // Feel free to write more unit tests for your own helper methods -- more details can be found in the handout!




    /**
     * A class to test basic decision tree functionality on a basic training dataset
     */


        /**
         /**
         /**
         * Constructs the decision tree for testing based on the input file and the target attribute.
         */
        @Before
        public void initialize() {
            List<Row> dataObjectsWeather = DecisionTreeCSVParser.parse(this.trainingPathWeather);
            List<String> attributeListWeather = new ArrayList<>(dataObjectsWeather.get(0).getAttributes());
            this.trainingWeather = new Dataset(attributeListWeather, dataObjectsWeather, AttributeSelection.DESCENDING_ALPHABETICAL);
            // builds a TreeGenerator object and generates a tree for "foodType"

//        TODO: Uncomment this once you've implemented generateTree
            this.testGenerator = new TreeGenerator();
            this.testGenerator.generateTree(trainingWeather, this.targetAttributeWeather);

//
            List<Row> dataObjectsGame1 = DecisionTreeCSVParser.parse(this.trainingPathGame);
            List<String> attributeListGame1 = new ArrayList<>(dataObjectsGame1.get(0).getAttributes());
            this.trainingGame1 = new Dataset(attributeListGame1, dataObjectsGame1, AttributeSelection.RANDOM);
            this.checkGame = new TreeGenerator();
            this.checkGame.generateTree(trainingGame1, this.targetAttributeGame);

            List<Row> dataObjectsFruit = DecisionTreeCSVParser.parse(this.trainingPathFruit);
            List<String> attributeListFruit = new ArrayList<>(dataObjectsFruit.get(0).getAttributes());
            this.trainingFruit = new Dataset(attributeListFruit, dataObjectsFruit, AttributeSelection.ASCENDING_ALPHABETICAL);
            this.checkFruit = new TreeGenerator();
            this.checkFruit.generateTree(trainingFruit, this.targetAttributeFruit);

//
//
            List<Row> dataObjectscountry= DecisionTreeCSVParser.parse(this.trainingPathcountry);
            List<String> attributeListcountry = new ArrayList<>(dataObjectscountry.get(0).getAttributes());
            this.country = new Dataset(attributeListcountry, dataObjectscountry, AttributeSelection.RANDOM);
            this.trainingcountry = new TreeGenerator();
            this.trainingcountry.generateTree(country, this.targetAttributcountry);

            // Below are splitDataTest, splitDataA, and splitDataB. I created these CSVs to test splitData
            // One of splitDataTest's attribute type is "alphabet". Each row has either "a" or "b".
            // splitDataA has all the rows with "a" in splitDataTest.
            // splitDataB has all the rows with "b" in splitDataTest.

            List<Row> dataObjectsSD = DecisionTreeCSVParser.parse(this.pathSplitDataTest);
            List<String> attributeListSD = new ArrayList<>(dataObjectsSD.get(0).getAttributes());
            this.splitDataTest = new Dataset(attributeListSD, dataObjectsSD, AttributeSelection.ASCENDING_ALPHABETICAL);

            // Creating splitDataA from csv
            List<Row> dataObjectsSDA = DecisionTreeCSVParser.parse(this.pathSplitDataA);
            List<String> attributeListSDA = new ArrayList<>(dataObjectsSDA.get(0).getAttributes());
            this.splitDataA = new Dataset(attributeListSDA, dataObjectsSDA, AttributeSelection.ASCENDING_ALPHABETICAL);

            // Creating splitDataB from csv
            List<Row> dataObjectsSDB = DecisionTreeCSVParser.parse(this.pathSplitDataB);
            List<String> attributeListSDB = new ArrayList<>(dataObjectsSDB.get(0).getAttributes());
            this.splitDataB = new Dataset(attributeListSDB, dataObjectsSDB, AttributeSelection.ASCENDING_ALPHABETICAL);
        }

        /**
         * Tests the getDefault method for Game Dataset and Weather Dataset
         */
        @Test
        public void testGetDefault() {
            List<Row> dataObjectsGame = DecisionTreeCSVParser.parse(this.trainingPathGame);
            List<String> attributeListGame = new ArrayList<>(dataObjectsGame.get(0).getAttributes());
            Dataset trainingGame = new Dataset(attributeListGame, dataObjectsGame, AttributeSelection.ASCENDING_ALPHABETICAL);
            Assert.assertEquals("buy", trainingGame.getDefault("outcome") );
            Assert.assertEquals("No", trainingGame.getDefault("Has guns") );


            List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPathWeather);
            List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
            Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);
            Assert.assertEquals("TRUE", training.getDefault("event") );

            List<Row> dataObjectsFruit = DecisionTreeCSVParser.parse(this.trainingPathFruit);
            List<String> attributeListFruit = new ArrayList<>(dataObjectsFruit.get(0).getAttributes());
            Dataset trainingFruit = new Dataset(attributeListFruit, dataObjectsFruit, AttributeSelection.ASCENDING_ALPHABETICAL);
            Assert.assertEquals("vegetable", trainingFruit.getDefault("foodType") );

        }

        /**
         * Tests the get decision
         */
        @Test
        public void testGetDecision() {
            Row Game7 = new Row("test row Game7");// makes a new (partial) Row representing the tangerine from the example

            Game7.setAttributeValue("Price", "50");
            Game7.setAttributeValue("Has songs", "No");
            Game7.setAttributeValue("Has guns", "No");
            Assert.assertEquals("buy", this.checkGame.getDecision(Game7));

            Row Game8 = new Row("test row Game8");
            Game8.setAttributeValue("Price", "10");
            Game8.setAttributeValue("Has songs", "well");
            Game8.setAttributeValue("Has guns", "sorry");
            Assert.assertEquals("buy", this.checkGame.getDecision(Game8));

            /**
             * Tests a partner data set
             */
            Row day7 = new Row("test row day7");
            day7.setAttributeValue("weather", "sunny");
            day7.setAttributeValue("temperature", "warm");
            day7.setAttributeValue("distance", "far");
            Assert.assertEquals("TRUE", this.testGenerator.getDecision(day7));

            /**
             * Tests given data set
             */
            Row tangerine = new Row("test row tangerine)");
            tangerine.setAttributeValue("color", "orange");
            tangerine.setAttributeValue("highProtein", "false");
            tangerine.setAttributeValue("calories", "high");
            Assert.assertEquals("fruit", this.checkFruit.getDecision(tangerine));

            /**
             * Tests a simple data set with one node
             */
            Row clim = new Row("climate outcome");
            Row climate = new Row("climate outcome2");
            clim.setAttributeValue("country", "south");
            climate.setAttributeValue("country", "east");
            Assert.assertEquals("tropical", this.trainingcountry.getDecision(clim));
            Assert.assertEquals("winter", this.trainingcountry.getDecision(climate));

        }

    /**
     * Tests the Exception of empty datasets
     */
    @Test (expected = RuntimeException.class)
    public void testRuntimeException() {
        List<Row> dataObjectsemptyh = DecisionTreeCSVParser.parse(this.trainingPathemptyh);
        List<String> attributeListemptyh = new ArrayList<>(dataObjectsemptyh.get(0).getAttributes());
        this.emptyh = new Dataset(attributeListemptyh, dataObjectsemptyh, AttributeSelection.RANDOM);
        this.trainingemptyh = new TreeGenerator();
        this.trainingemptyh.generateTree(emptyh, this.targetAttributeemptyH);

        Row emp = new Row("empty row heading)");
        emp.setAttributeValue("E", "");
        emp.setAttributeValue("M", "");
        emp.setAttributeValue("P", "");
        emp.setAttributeValue("T", "");
        emp.setAttributeValue("Y", "");
//        Assert.assertNull(this.trainingemptyh.getDecision(emp));
        this.trainingemptyh.getDecision(emp);


        List<Row> dataObjectsempty = DecisionTreeCSVParser.parse(this.trainingPathempty);
        List<String> attributeListempty = new ArrayList<>(dataObjectsempty.get(0).getAttributes());
        this.empty = new Dataset(attributeListempty, dataObjectsempty, AttributeSelection.DESCENDING_ALPHABETICAL);
        this.trainingempty = new TreeGenerator();
        this.trainingempty.generateTree(empty, this.targetAttributeempty);

        Row empy = new Row("empty row heading)");
        emp.setAttributeValue("", "");
        this.trainingempty.getDecision(empy);
    }

    /**
     * Tests the size method in Dataset
     */
    @Test
    public void testSize() {
        //getting the number of rows in each dataset
        Assert.assertEquals(5, this.trainingWeather.size());
        Assert.assertEquals(6, this.trainingGame1.size());
        Assert.assertEquals(7, this.trainingFruit.size());

    }

    /**
     * Tests the getValue method in Dataset
     */
    @Test
    public void testGetValue() {
        // getting the first row values of weather
        Assert.assertEquals("cloudy", this.trainingWeather.getValue("weather"));
        Assert.assertEquals("cold", this.trainingWeather.getValue("temperature"));
        Assert.assertEquals("far", this.trainingWeather.getValue("distance"));
        Assert.assertEquals("FALSE", this.trainingWeather.getValue("event"));

        // getting the first row values of fruit/veggies
        Assert.assertEquals("green", this.trainingFruit.getValue("color"));
        Assert.assertEquals("true", this.trainingFruit.getValue("highProtein"));
        Assert.assertEquals("low", this.trainingFruit.getValue("calories"));
        Assert.assertEquals("vegetable", this.trainingFruit.getValue("foodType"));

        // getting the first row values of game
        Assert.assertEquals("50", this.trainingGame1.getValue("Price"));
        Assert.assertEquals("Yes", this.trainingGame1.getValue("Has songs"));
        Assert.assertEquals("No", this.trainingGame1.getValue("Has guns"));
        Assert.assertEquals("buy", this.trainingGame1.getValue("outcome"));

    }

    /**
     * Tests the splitData method in Dataset
     */
    @Test
    public void testSplitData() {
        // For descriptions of what splitDataTest is, refer to the @Before section
        // Now I'm using .splitData on splitDataTest by the attribute type "alphabet"
        List<Dataset> splitDataList = this.splitDataTest.splitData("alphabet");

        //I tried to test by comparing splitDataA and splitDataB from the datasets in splitDataList
        //Test failed because the attributeAccesses field in the Row class kept getting in the way
        //I chose a more indirect way of testing by checking the size and the first row values for the datasets in splitDataList
        Assert.assertEquals(2, splitDataList.size());
        Assert.assertEquals("1", splitDataList.get(0).getValue("number"));
        Assert.assertEquals("red", splitDataList.get(0).getValue("color"));
        Assert.assertEquals("4", splitDataList.get(1).getValue("number"));
        Assert.assertEquals("blue", splitDataList.get(1).getValue("color"));
    }

    /**
     * Tests the isSameAttribute method in Dataset
     */
    @Test
    public void testIsSameAttribute() {
        // Using splitDataB here
        // 2 rows, all the attribute values under "color" should be "blue"
        Assert.assertTrue(this.splitDataB.isSameAttribute("color"));
    }

    /**
     * Tests the isAttributeListEmpty method in Dataset
     */
    @Test
    public void testIsAttributeListEmpty() {
        String pathSplitDataTest = "data/splitdata.csv";

        // using splitData on dataset until it removes all the attribute types from the dataset's attributeList
        // Reminder: each time splitData is used, it removes the attribute type it split on in the Dataset's attributeList
        Dataset splitDataTestA = this.splitDataTest.splitData("alphabet").get(0); //Removes "alphabet"
        Dataset splitDataTestANum = splitDataTestA.splitData("number").get(0); //Removes "number"
        Dataset splitDataTestANumCol = splitDataTestANum.splitData("color").get(0); //Removes "color"

        // Assertion that the dataset's attributeList is now empty
        Assert.assertTrue(splitDataTestANumCol.isAttributeListEmpty());
    }

    /**
     * Tests the getAttributeList method in Dataset
     */
    @Test
    public void testGetAttributeList() {
        List<String> splitDataAttri = this.splitDataTest.getAttributeList();
        List<String> weatherAttri = this.trainingWeather.getAttributeList();
        List<String> fruitAttri = this.trainingFruit.getAttributeList();

        System.out.println(splitDataAttri); // comes out correct
        System.out.println(weatherAttri); // list is missing event
        System.out.println(fruitAttri); // list is missing foodType
        //also how is the order of the list chosen????

    }

    /**
     * Tests the getDataObjects method in Dataset
     */
    @Test
    public void testGetDataObjects() {
        //we're not testing getters, right?
        //this is a getter, so confirm if we don't need to test it and then delete it
        List<Row> dataObjectsFruit = DecisionTreeCSVParser.parse(this.trainingPathFruit);
        List<String> attributeListFruit = new ArrayList<>(dataObjectsFruit.get(0).getAttributes());
        this.trainingFruit = new Dataset(attributeListFruit, dataObjectsFruit, AttributeSelection.ASCENDING_ALPHABETICAL);
        this.checkFruit = new TreeGenerator();
        this.checkFruit.generateTree(trainingFruit, this.targetAttributeFruit);
        Assert.assertEquals(dataObjectsFruit, this.trainingFruit.getDataObjects());
    }




}
