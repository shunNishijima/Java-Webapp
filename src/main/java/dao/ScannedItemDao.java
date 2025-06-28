package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.ItemModel;
import model.ScannedItemModel;

public class ScannedItemDao {

    public static final List<ScannedItemModel> scannedItems = new ArrayList<>();

        public void addToCart(ScannedItemModel scannedItem) {
            scannedItems.add(scannedItem);

        }
        public boolean updateAmount(int scannedItemId, int amount) { // we take the scanned item, then update the amount
            for (ScannedItemModel scannedItem : scannedItems) // loop through each item in the scannedItem
                if (scannedItem.getItem().getId() == scannedItemId) {
                    switch(amount) {
                        case -1: // decrease the amount by 1
                            scannedItem.setAmount(scannedItem.getAmount() - 1);
                            if (scannedItem.getAmount() == 0)
                                removeScannedItem(scannedItemId);
                            break;
                        case 1: // increase the amount by 1
                            scannedItem.setAmount(scannedItem.getAmount() + 1);
                            break;
                    }

                    return true;
                }

            return false;
        }

        public boolean removeScannedItem(int scannedItemId) {
            for (ScannedItemModel scannedItem : scannedItems) {
                if (scannedItem.getItem().getId() == scannedItemId) {
                    scannedItems.remove(scannedItem);
                    return true;
                }
            }

            return false;
        }

        public static List<ScannedItemModel> getScannedItems() {
            return scannedItems;
        }

        public HashMap<Integer, ScannedItemModel> returnScannedItems() {
            HashMap<Integer, ScannedItemModel> result = new HashMap<>();
            for (int i =0; i < scannedItems.size(); i++) {
                result.put(i, scannedItems.get(i));
            }
            return result;
        }

}


