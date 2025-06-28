package resorces;

import dao.ScannedItemDao;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import model.ScannedItemModel;

import java.util.HashMap;

@Path("/scanneditems")
public class ScannedItemsResource {
    private ScannedItemDao scannedItemDao = new ScannedItemDao();

    //TODO: make it return correct JSON form. maybe {items : [scannedItem, scannedItem, etc etc]
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<Integer, ScannedItemModel> addItemToCart(ScannedItemModel scannedItem) {

            for (ScannedItemModel item : scannedItemDao.returnScannedItems().values()) {
                if (scannedItem.getItem().getId() == item.getItem().getId()) { // if it already existed
                    System.out.println("existed!");
                    return scannedItemDao.returnScannedItems(); // return immediately
                }
            }
        if (scannedItem.getAmount() > 0) {
            scannedItemDao.addToCart(scannedItem);
        }
        return scannedItemDao.returnScannedItems();
    }

    @PUT
    @Path("/{scannedItemId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<Integer, ScannedItemModel> updateItemAmount(@PathParam("scannedItemId") int scannedItemId, String amount) {
        scannedItemDao.updateAmount(scannedItemId, Integer.parseInt(amount));
        return scannedItemDao.returnScannedItems();
    }

    @DELETE
    @Path("/{scannedItemId}")
    public void deleteItem(@PathParam("scannedItemId") int scannedItemId) {
        scannedItemDao.removeScannedItem(scannedItemId);
    }
}

