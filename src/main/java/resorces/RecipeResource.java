package resorces;

import dao.RecipeDao;
import dao.ScannedItemDao;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.sql.Array;
import java.util.*;
import model.IngredientModel;
import model.RecipeModel;

import model.ScannedItemModel;

@Path("/recipes")
public class RecipeResource {
    private RecipeDao recipeDao;
    public RecipeResource() {
        recipeDao = new RecipeDao();
        if (recipeDao.getAllRecipes().size() == 10)
            ;
        else
            RecipeDao.initializeMockDataset();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<Integer, RecipeModel> getRecipe() {
        List<ScannedItemModel> allScannedItems = ScannedItemDao.getScannedItems();
        HashMap<RecipeModel, Double> recipeScores = new HashMap<>();

        for (RecipeModel recipe : recipeDao.getAllRecipes()) { // for each recipe
            double finalScore = 0;
            List<IngredientModel> ingredientsRequired = recipe.getIngredients();

            for (ScannedItemModel scannedItem : allScannedItems) {
                double scorePerItem = recipeDao.calculateScore(scannedItem, ingredientsRequired);
                finalScore += scorePerItem;
            }

            recipeScores.put(recipe, finalScore);
        }

        List<Map.Entry<RecipeModel, Double>> entryList = new ArrayList<>(recipeScores.entrySet());
        entryList.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        HashMap<Integer, RecipeModel> top3Recipes = new HashMap<>();
        int count = 0;


        for (Map.Entry<RecipeModel, Double> entry : entryList) {
            top3Recipes.put(count, entry.getKey());
            count++;

            if (count == 3) break;
        }
        
        return top3Recipes;
    }

    @GET
    @Path("/{recipeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public RecipeModel getRecipe(@PathParam("recipeId") int recipeId) {
        return recipeDao.getRecipeById(recipeId);
    }

    @PUT
    @Path("/{recipeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRecipe(@PathParam("recipeId") int recipeId, RecipeModel recipe) {
        recipe.setId(recipeId);
        recipeDao.updateRecipe(recipe);
    }

    @DELETE
    @Path("/{recipeId}")
    public void deleteRecipe(@PathParam("recipeId") int recipeId) {
        recipeDao.deleteRecipe(recipeId);
    }
}
