package dao;

// ItemDao.java
import model.ItemModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDao {
    private static Map<Integer, ItemModel> items = new HashMap<>();
    private static int idCounter = 1;

    public List<ItemModel> getAllItems() {
        return new ArrayList<>(items.values());
    }

    public ItemModel getItemById(int id) {
        return items.get(id);
    }

    public void addItem(ItemModel item) {
        item.setId(idCounter++);
        items.put(item.getId(), item);
    }

    public void deleteItem(int id) {
        items.remove(id);
    }

    public void initializeMockDataset() {
        addItem(new ItemModel(1, "Apple", "Fresh and juicy",150));
        addItem(new ItemModel(2, "Broccoli", "Green curly hair",75));
        addItem(new ItemModel(3, "Beef", "Mooooo",150));
        addItem(new ItemModel(4, "Carrot", "Watch out for the rabbit",75));
        addItem(new ItemModel(5, "Egg", "Organic brown eggs",50));
        addItem(new ItemModel(6, "Onion", "Cry cry",45));
        addItem(new ItemModel(7, "Chicken", "🐔",150));
        addItem(new ItemModel(8, "Lettuce", "Yummy salad",200));
        addItem(new ItemModel(9, "Tomato", "Ketchup",10));
        addItem(new ItemModel(10, "Cinnamon", "Smells weird",5));
        addItem(new ItemModel(11, "Butter", "Jungkook is that you?",10));
        addItem(new ItemModel(12, "Bell Pepper", "Not bell curved distribution",5));

    }
}
