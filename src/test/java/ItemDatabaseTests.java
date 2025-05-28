import isd.group_4.Item;
import isd.group_4.database.DAO;
import isd.group_4.database.ItemDatabaseManager;

import org.junit.Test;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ItemDatabaseTests {

    @Test
    public void testAddItem() throws SQLException {
        DAO dao = new DAO();
        ItemDatabaseManager itemDB = dao.Items();

        Item newItem = new Item(9999, "Test Item", "JUnit Test Description", 10, 123.45);
        int result = itemDB.add(newItem);
        assertTrue("Item should be inserted successfully", result > 0);
    }

    @Test
    public void testSearchItemsWithSort() throws SQLException {
        DAO dao = new DAO();
        ItemDatabaseManager itemDB = dao.Items();

        List<Item> results = itemDB.searchItemsWithSort("apple", "asc");
        assertFalse("Should return at least one result", results.isEmpty());

        for (int i = 0; i < results.size() - 1; i++) {
            assertTrue("Items should be sorted in ascending order",
                    results.get(i).getPrice() <= results.get(i + 1).getPrice());
        }
    }

    @Test
    public void testUpdateItem() throws SQLException {
        DAO dao = new DAO();
        ItemDatabaseManager itemDB = dao.Items();

        Item updatedItem = new Item(1, "Updated Name", "Updated Desc", 50, 99.99);
        boolean result = itemDB.update(1, updatedItem);
        assertTrue("Item should be updated successfully", result);

        Item reloaded = itemDB.get(1);
        assertEquals("Updated Name", reloaded.getItemName());
        assertEquals("Updated Desc", reloaded.getItemDescription());
        assertEquals(99.99, reloaded.getPrice(), 0.01);
        assertEquals(50, reloaded.getQuantity());
    }

    @Test
    public void testDeleteItem() throws SQLException {
        DAO dao = new DAO();
        ItemDatabaseManager itemDB = dao.Items();

        Item itemToDelete = new Item(9998, "Delete Me", "Temporary", 1, 5.0);
        itemDB.add(itemToDelete);

        boolean result = itemDB.delete(9998);
        assertTrue("Item should be deleted successfully", result);

        Item shouldBeNull = itemDB.get(9998);
        assertNull("Deleted item should not exist in database", shouldBeNull);
    }
}