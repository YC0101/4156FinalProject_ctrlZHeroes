package dev.coms4156.project.finalproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * This class contains tests for the MyFileDatabase class.
 */
@SpringBootTest
@ContextConfiguration
public class MyFileDatabaseTests {

  /**
   * Create variable for testing.
   */
  @BeforeAll
  public static void setupForTesting() {
    testDatabase = new MyFileDatabase(true, "./resourceData.txt", "./requestData.txt");
    testDatabaseSave = new MyFileDatabase(true, "./testResourceData.txt", "./testRequestData.txt");

    Item item1 = new Item("Food", 10, LocalDate.now().plusDays(7), "Robert");
    HashMap<String, Item> items = new HashMap<>();
    items.put(item1.getItemId(), item1);
    Resource resource1 = new Resource(items, "R_TEST");
    resourceMapping = new HashMap<>();
    resourceMapping.put("R_TEST", resource1);

    Request request1 = new Request("REQ1", Arrays.asList("ABCD", "EFGH"), 
    "Pending", "High", "John Doe");
    scheduler = new Scheduler(Arrays.asList(request1));
  }

  @Test
  public void setResourceTest() {
    testDatabase.setResources(resourceMapping);
    assertEquals(resourceMapping, testDatabase.getResources());
  }

  @Test
  public void setRequestTest() {
    testDatabase.setRequests(scheduler);
    assertEquals(scheduler, testDatabase.getRequests());
  }

  @Test
  public void deSerializeObjectFromFileTest() {
    System.out.println("Print: " + testDatabase.deSerializeResourcesFromFile());
    System.out.println("Print: " + testDatabase.deSerializeRequestsFromFile());
  }

  /** The testdata.txt output can be find in IndividualProject/testdata.txt. */
  @Test
  public void saveContentsToFileTest() {
    testDatabaseSave.setResources(resourceMapping);
    testDatabaseSave.setRequests(scheduler);
    testDatabaseSave.saveResourcesToFile();
    testDatabaseSave.saveRequestsToFile();
  }

  /** The test database instance for testing. */
  public static MyFileDatabase testDatabase;
  public static MyFileDatabase testDatabaseSave;

  public static HashMap<String, Resource> resourceMapping;
  public static Scheduler scheduler;
}