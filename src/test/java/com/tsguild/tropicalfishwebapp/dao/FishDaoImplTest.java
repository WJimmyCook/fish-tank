/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tropicalfishwebapp.dao;

import com.tsguild.tropicalfishwebapp.model.Fish;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Jimmy Cook
 */
public class FishDaoImplTest {

    private FishDao testDao;

    public FishDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        testDao = (FishDao) ctx.getBean("fishDao");
        
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("DELETE FROM Fish");

    }

    Fish[] fishForTesting = {
        new Fish(-1, "Goldfish", "Goldyfish", "Freshwater", "example.com/fish1.png"),
        new Fish(-1, "redfish", "sciencefish", "Freshwater", "example.com/fish2.png"),
        new Fish(-1, "bluefish", "spacefish", "Saltwater", "example.com/fish3.png"),
        new Fish(-1, "onefish", "wateriswet", "Freshwater", "example.com/fish4.png"),
        new Fish(-1, "twofish", "sandisdry", "Saltwater", "example.com/fish5.png")
    };

    Fish[] duplicateFish = {
        new Fish(-1, "Goldfish", "Goldyfish", "Freshwater", "example.com/fish1.png"),
        new Fish(-1, "redfish", "sciencefish", "Freshwater", "example.com/fish2.png"),
        new Fish(-1, "bluefish", "spacefish", "Saltwater", "example.com/fish3.png"),
        new Fish(-1, "onefish", "wateriswet", "Freshwater", "example.com/fish4.png"),
        new Fish(-1, "twofish", "sandisdry", "Saltwater", "example.com/fish5.png")
    };

    Fish[] similarFish = {
        new Fish(-1, "Blowfish", "Goldyfish", "Freshwater", "example.com/fish1.png"),
        new Fish(-1, "Hootie", "And", "The", "Blowfish.com"),
        new Fish(-1, "sadfish", "spacefish", "Saltwater", "example.com/fish3.png"),
        new Fish(-1, "madfish", "wateriswet", "Freshwater", "example.com/fish4.png"),
        new Fish(-1, "catfish", "sandisdry", "Saltwater", "example.com/fish5.png")
    };

    /**
     * Test of addFish method, of class FishDaoImpl.
     */
    @Test
    public void testAddFish() {
        Fish fish = new Fish(-1, "redfish", "fishy", "Saltwater", "go.com/fish");

        Fish result = testDao.addFish(fish);

        Fish expResult = testDao.getFishByID(fish.getId());
        assertEquals(expResult, result);
    }

    @Test
    public void testAgainstEmptyDao() {

        Assert.assertNull("Asking for a non existant fish should return null.", testDao.getFishByID(445));
        Assert.assertNotNull("List of all fish should not be null.", testDao.getAllFish());
        Assert.assertEquals("Expected fish count of 'all fish' is nonzero with empty DAO.", 0, testDao.getAllFish().size());
    }

    @Test
    public void testAddOneFish() {
        Fish fishToAdd = fishForTesting[0];
        testDao.addFish(fishToAdd);

        Assert.assertEquals("Returned fish does not match expected.", fishToAdd, testDao.getFishByID(fishToAdd.getId()));
        Assert.assertNotNull("List of all fish should not be null.", testDao.getAllFish());
        Assert.assertEquals("Expected fish count of 'all fish' does not match after adding one fish.", 1, testDao.getAllFish().size());
        Assert.assertTrue("Returned fish in getAllFish does not match expected.", testDao.getAllFish().contains(fishToAdd));

    }

    @Test
    public void testUpdateFish() {
        testDao.addFish(fishForTesting[0]);
        similarFish[0].setId(fishForTesting[0].getId());
        testDao.updateFish(similarFish[0]);

        Assert.assertEquals("Updated fish get does not match expected.", similarFish[0], testDao.getFishByID(similarFish[0].getId()));
        Assert.assertNotNull("List of all fish should not be null.", testDao.getAllFish());
        Assert.assertEquals("Expected fish count of 'all fish' does not match after replacing one fish.", 1, testDao.getAllFish().size());
        Assert.assertTrue("Returned fish in getAllFish does not match expected.", testDao.getAllFish().contains(similarFish[0]));
    }

    @Test
    public void testAddMultipleFish() {
        for (Fish fish : fishForTesting) {
            testDao.addFish(fish);
        }

        Assert.assertNotNull("List of all fish should not be null.", testDao.getAllFish());
        Assert.assertEquals("Expected fish count of 'all fish' does not match after adding several fish.",
                fishForTesting.length, testDao.getAllFish().size());

        for (int i = 0; i < fishForTesting.length; i++) {
            Assert.assertEquals("Returned fish does not match expected.", fishForTesting[i], testDao.getFishByID(fishForTesting[i].getId()));
            Assert.assertTrue("Returned fish in getAllFish does not match expected.", testDao.getAllFish().contains(fishForTesting[i]));
        }
    }

    @Test
    public void testUpdateMultipleFish() {
        for (Fish fish : fishForTesting) {
            testDao.addFish(fish);
        }

        for (int i = 0; i < fishForTesting.length; i++) {
            similarFish[i].setId(fishForTesting[i].getId());
            testDao.updateFish(similarFish[i]);
        }

        Assert.assertNotNull("List of all fish should not be null.", testDao.getAllFish());
        Assert.assertEquals("Expected fish count of 'all fish' does not match after replacing several fish.",
                fishForTesting.length, testDao.getAllFish().size());

        for (int i = 0; i < similarFish.length; i++) {
            Assert.assertEquals("Get fish does not match expected return on update.", similarFish[i], testDao.getFishByID(similarFish[i].getId()));
            Assert.assertTrue("Returned fish in getAllFish does not match expected.", testDao.getAllFish().contains(similarFish[i]));
        }
    }

    @Test
    public void testAddSimilarFish() {
        for (Fish fish : fishForTesting) {
            testDao.addFish(fish);
        }

        for (Fish fish : similarFish) {
            testDao.addFish(fish);
        }

        Assert.assertNotNull("List of all fish should not be null.", testDao.getAllFish());
        Assert.assertEquals("Expected fish count does not match after adding several similar fish.",
                fishForTesting.length + similarFish.length, testDao.getAllFish().size());
        Assert.assertEquals("Expected fish count of 'all fish' does not match after adding several similar fish.",
                fishForTesting.length + similarFish.length, testDao.getAllFish().size());

        for (int i = 0; i < similarFish.length; i++) {
            Assert.assertEquals("Get fish does not match expected return on addition of similar fish.", similarFish[i],
                    testDao.getFishByID(similarFish[i].getId()));
            Assert.assertTrue("Returned fish in getAllFish does not match expected.", testDao.getAllFish().contains(similarFish[i]));
        }

        for (int i = 0; i < fishForTesting.length; i++) {
            Assert.assertEquals("Get fish does not match expected return on addition of similar fish.", fishForTesting[i],
                    testDao.getFishByID(fishForTesting[i].getId()));
            Assert.assertTrue("Returned fish in getAllFish does not match expected.", testDao.getAllFish().contains(fishForTesting[i]));
        }
    }
    
    @Test
    public void testAddAndRemoveOneFish() {
        testDao.addFish(fishForTesting[0]);
        testDao.removeFish(fishForTesting[0].getId());

        Assert.assertNotNull("List of all fish should not be null.", testDao.getAllFish());
        Assert.assertNull("Get fish should return null after being removed.", testDao.getFishByID(fishForTesting[0].getId()));
        Assert.assertEquals("Expected fish count of 'all Fish' should be zero when adding/removing a single fish.", 0, testDao.getAllFish().size());
    }
    
    @Test
    public void testAddAndRemoveFishTwice() {
        testDao.addFish(fishForTesting[0]);
        testDao.removeFish(fishForTesting[0].getId());
        testDao.removeFish(fishForTesting[0].getId());

        Assert.assertNotNull("List of all fish should not be null.", testDao.getAllFish());
        Assert.assertNull("Fish should return null after being removed.", testDao.getFishByID(fishForTesting[0].getId()));
        Assert.assertEquals("Expected fish count of 'all fish' should be zero when adding/removing a single fish twice.", 0, testDao.getAllFish().size());
    }
    
    @Test
    public void testAddAndRemoveMultipleFish() {

        for (Fish fish : fishForTesting) {
            testDao.addFish(fish);
        }

        int fishAdded = fishForTesting.length;
        for (int i = 0; i < fishForTesting.length; i += 2) {
            testDao.removeFish(fishForTesting[i].getId());
            fishAdded--;
        }

        Assert.assertNotNull("List of all fish should not be null.", testDao.getAllFish());
        Assert.assertEquals("Expected fish count of 'all fish' does not match after adding & removing several fish.",
                fishAdded, testDao.getAllFish().size());

        for (int i = 0; i < fishForTesting.length; i++) {
            if (i % 2 == 1) {
                Assert.assertEquals("Returned fish does not match expected.", fishForTesting[i], testDao.getFishByID(fishForTesting[i].getId()));
            } else {
                Assert.assertNull("Fish should be removed and return null.", testDao.getFishByID(fishForTesting[i].getId()));
            }
        }
    }
    
    @Test
    public void testAddAndRemoveFishMultipleTimes() {

        for (Fish fish : fishForTesting) {
            testDao.addFish(fish);
        }

        for (Fish fish : fishForTesting) {
            testDao.removeFish(fish.getId());
        }

        for (Fish fish : fishForTesting) {
            testDao.addFish(fish);
        }

        Assert.assertNotNull("List of all fish should not be null.", testDao.getAllFish());
        Assert.assertEquals("Expected fish count of 'all fish' should be zero when adding/removing a all fish.",
                fishForTesting.length, testDao.getAllFish().size());



        for (int i = 0; i < fishForTesting.length; i++) {
            Fish fish = fishForTesting[i];
            Assert.assertEquals("Fish should return after being re-added.", fish, testDao.getFishByID(fish.getId()));
            testDao.removeFish(fish.getId());
            Assert.assertNull("Fish should return null after being removed.", testDao.getFishByID(fish.getId()));
        }

        Assert.assertEquals("Expected fish count of 'all fish' should be zero when adding/removing a all fish.", 0, testDao.getAllFish().size());
    }
    
    @Test
    public void testFishCountOnAddition() {

        // Add all fish and check that count increments appropriately
        for (int i = 0; i < fishForTesting.length; i++) {
            testDao.addFish(fishForTesting[i]);
            Assert.assertEquals("Expected " + (i + 1) + " fish after adding fishes.",
                    i + 1, testDao.getAllFish().size());
        }

    }
    
    @Test
    public void testFishCountOnUpdate() {

        // Add all fish and check that count increments appropriately
        for (int i = 0; i < fishForTesting.length; i++) {
            testDao.addFish(fishForTesting[i]);
            similarFish[i].setId(fishForTesting[i].getId());
            testDao.updateFish(similarFish[i]);
            Assert.assertEquals("Expected " + (i + 1) + " fish after updating fishes.",
                    i + 1, testDao.getAllFish().size());
        }

    }

    @Test
    public void testFishCountAfterRemoval() {

        // Add all fish
        for (int i = 0; i < fishForTesting.length; i++) {
            testDao.addFish(fishForTesting[i]);
        }

        // Remove fish one by one and test that count decrements properly
        for (int i = 0; i < fishForTesting.length; i++) {
            testDao.removeFish(fishForTesting[i].getId());
            Assert.assertEquals("Expected " + (fishForTesting.length - i - 1) + " fish after removing fishes.",
                    fishForTesting.length - i - 1, testDao.getAllFish().size());
        }
    }

    @Test
    public void testFishAfterRemovalOfNonExistent() {

        // Add all fish
        for (int i = 0; i < fishForTesting.length; i++) {
            testDao.addFish(fishForTesting[i]);
        }

        testDao.removeFish(100);
        Assert.assertEquals("Expected " + fishForTesting.length + " fish after removing fishes.",
                fishForTesting.length, testDao.getAllFish().size());

    }

    @Test
    public void testFishCountAfterTwiceRemoved() {

        // Add all fish
        for (int i = 0; i < fishForTesting.length; i++) {
            testDao.addFish(fishForTesting[i]);
        }

        // Remove fish one by one and test that count decrements properly
        for (int i = 0; i < fishForTesting.length; i++) {
            testDao.removeFish(fishForTesting[i].getId());
        }

        Assert.assertEquals("Expected " + 0 + " fish after removing fishes.",
                0, testDao.getAllFish().size());

        // Remove fish one by one and test that count decrements properly
        for (int i = 0; i < fishForTesting.length; i++) {
            testDao.removeFish(fishForTesting[i].getId());
        }

        Assert.assertEquals("Expected " + 0 + " fish after attempting to re-remove fishes.",
                0, testDao.getAllFish().size());

    }
    
    @Test 
    public void testSearch(){
        
        // Add all fish
        for (int i = 0; i < fishForTesting.length; i++) {
            testDao.addFish(fishForTesting[i]);
        }
        
        Assert.assertEquals("Expected 1 fish after search.", 1, 
                testDao.searchFishByName("Goldfish").size());
        
        for(int i = 0; i < fishForTesting.length; i++){
        Assert.assertEquals("expected fish to match", testDao.searchFishByName(fishForTesting[i].getName()).get(0),fishForTesting[i]);
        }
    }
}
