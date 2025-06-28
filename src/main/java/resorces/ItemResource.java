package resorces;

// ItemResource.java
import dao.ItemDao;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import model.ItemModel;
import java.util.List;

@Path("/items")
public class ItemResource {
    private ItemDao itemDao = new ItemDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemModel> getAllItems() {
        return itemDao.getAllItems();
    }

    @GET
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ItemModel getItemById(@PathParam("itemId") int itemId) {
        itemDao.initializeMockDataset();
        return itemDao.getItemById(itemId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addItem(ItemModel item) {
        itemDao.addItem(item);
    }

//    @PUT
//    @Path("/{itemId}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void updateItem(@PathParam("itemId") int itemId, ItemModel item) {
//        item.setId(itemId);
//        itemDao.updateItem(item);
//    }

    @DELETE
    @Path("/{itemId}")
    public void deleteItem(@PathParam("itemId") int itemId) {
        itemDao.deleteItem(itemId);
    }
}
