package dao;

import java.sql.Array;
import java.util.*;
import model.IngredientModel;
import model.ItemModel;
import model.RecipeModel;
import model.ScannedItemModel;

public class RecipeDao {
    private static final Map<Integer, RecipeModel> recipeDatabase = new HashMap<>();
    private static int idCounter = 1;

    public RecipeModel getRecipeById(int id) {

        return recipeDatabase.get(id);
    }

    public static int createRecipe(RecipeModel recipe) {
        int newRecipeId = idCounter++;
        recipe.setId(newRecipeId);
        recipeDatabase.put(newRecipeId, recipe);
        return newRecipeId;
    }

    public boolean updateRecipe(RecipeModel recipe) {
        if (recipeDatabase.containsKey(recipe.getId())) {
            recipeDatabase.put(recipe.getId(), recipe);
            return true;
        }
        return false;
    }

    public boolean deleteRecipe(int recipeId) {
        return recipeDatabase.remove(recipeId) != null;
    }

    public List<RecipeModel> getAllRecipes() {
        return new ArrayList<>(recipeDatabase.values());
    }
    public double calculateScore(ScannedItemModel scannedItem, List<IngredientModel> ingredientsRequired){
        double score = 0;

        for (IngredientModel ingredient : ingredientsRequired)
            if (scannedItem.getItem().getName().equals(ingredient.getName()))
                score += scannedItem.getAmount() / ingredient.getAmount();

        return score;
    }
    public static void initializeMockDataset() {
        createRecipe(new RecipeModel(1, "Broccoli Pho",
                                     new ArrayList<>(Arrays.asList(
                                             new IngredientModel("Broccoli", 5),
                                             new IngredientModel("Onion", 2),
                                             new IngredientModel("Sprout", 3),
                                             new IngredientModel("Rice noodle", 2),
                                             new IngredientModel("Carrot", 2),
                                             new IngredientModel("Chilli", 2),
                                             new IngredientModel("Ginger", 1))),
 "Char onion and ginger. Boil bones and brisket/flank, then clean. " +
         "Boil bones with charred ingredients, spices, salt, fish sauce, and sugar. Simmer for 1.5-2 hours. " +
        "Strain the broth." + "Slice beef thinly, cook noodles." + "Assemble bowls: noodles, raw beef slices, " +
     "and hot broth. " + "Serve with fresh herbs, bean sprouts, lime, and chili on the side. "));

        createRecipe(new RecipeModel(2, "Vegetable Stir Fry",
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Broccoli", 1),
                        new IngredientModel("Carrot", 2),
                        new IngredientModel("Bell Pepper", 2),
                        new IngredientModel("Soy Sauce", 1),
                        new IngredientModel("Ginger", 1),
                        new IngredientModel("Sesame Oil", 1))),
                "Cut vegetables into bite-sized pieces. " +
                        "Stir fry vegetables in sesame oil and ginger until tender. " +
                        "Add soy sauce and stir until well-coated. " +
                        "Serve hot as a side dish or over rice. Enjoy!"));

        createRecipe(new RecipeModel(3, "Caprese Salad",
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Tomato", 4),
                        new IngredientModel("Fresh Mozzarella", 1),
                        new IngredientModel("Basil Leaves", 0.5),
                        new IngredientModel("Balsamic Vinegar", 1),
                        new IngredientModel("Olive Oil", 1),
                        new IngredientModel("Salt", 0.5))),
                "Slice tomatoes and fresh mozzarella. " +
                        "Arrange on a plate, alternating with basil leaves. " +
                        "Drizzle with balsamic vinegar and olive oil. " +
                        "Sprinkle with salt to taste. Serve and enjoy!"));

        createRecipe(new RecipeModel(4, "Vegetarian Tacos",
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Black Beans", 1),
                        new IngredientModel("Corn Tortillas", 8),
                        new IngredientModel("Avocado", 2),
                        new IngredientModel("Lettuce", 1),
                        new IngredientModel("Salsa", 1),
                        new IngredientModel("Cilantro", 0.5))),
                "Heat black beans in a pan until warm. " +
                        "Warm corn tortillas on a griddle or in the oven. " +
                        "Fill tortillas with black beans, sliced avocado, lettuce, salsa, and cilantro. " +
                        "Serve and enjoy these delicious vegetarian tacos!"));

        // Recipe 1: Apple Walnut Salad
        createRecipe(new RecipeModel(5, "Apple Walnut Salad",
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Apple", 2),
                        new IngredientModel("Lettuce", 1),
                        new IngredientModel("Walnut", 1),
                        new IngredientModel("Tomato", 1))),
                "Slice apples, tomatoes, and lettuce. " +
                        "Toss them together with walnuts. " +
                        "Serve as a refreshing salad."));

        // Recipe 2: Broccoli and Carrot Stir-Fry
        createRecipe(new RecipeModel(6, "Broccoli and Carrot Stir-Fry",
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Broccoli", 1),
                        new IngredientModel("Carrot", 2),
                        new IngredientModel("Onion", 1))),
                "Stir-fry chopped broccoli, carrot, and onion in a pan. " +
                        "Season with your favorite herbs and spices. " +
                        "Enjoy as a colorful and nutritious side dish."));

        // Recipe 3: Grilled Chicken Lettuce Wraps
        createRecipe(new RecipeModel(7, "Grilled Chicken Lettuce Wraps",
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Lettuce", 4),
                        new IngredientModel("Chicken Breast", 2),
                        new IngredientModel("Tomato", 1))),
                "Grill chicken breast and slice it. " +
                        "Place chicken slices and tomato in lettuce leaves. " +
                        "Wrap and secure with toothpicks for a low-carb meal."));

        // Recipe 4: Tomato and Onion Omelette
        createRecipe(new RecipeModel(8, "Tomato and Onion Omelette",
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Tomato", 1),
                        new IngredientModel("Onion", 1),
                        new IngredientModel("Egg", 2))),
                "Dice tomato and onion. " +
                        "Whisk eggs, add diced vegetables, and make a healthy omelette."));

        // Recipe 5: Carrot and Apple Smoothie
        createRecipe(new RecipeModel(9, "Carrot and Apple Smoothie",
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Carrot", 1),
                        new IngredientModel("Apple", 1))),
                "Blend carrot and apple with water or yogurt. " +
                        "Enjoy a nutritious and refreshing smoothie."));

        // Recipe 6: Onion and Tomato Quinoa Bowl
        createRecipe(new RecipeModel(10, "Onion and Tomato Quinoa Bowl",
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Quinoa", 1),
                        new IngredientModel("Onion", 1),
                        new IngredientModel("Tomato", 1))),
                "Cook quinoa and top with sautéed onion and tomato. " +
                        "Season with herbs for a wholesome bowl."));

        // Recipe 7: Lettuce Wrap Tacos
        createRecipe(new RecipeModel(11, "Lettuce Wrap Tacos",
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Lettuce", 6),
                        new IngredientModel("Tomato", 1),
                        new IngredientModel("Onion", 1))),
                "Fill lettuce leaves with diced tomato and onion. " +
                        "Top with your favorite protein for a light taco."));

        // Recipe 8: Broccoli and Carrot Soup
        createRecipe(new RecipeModel(12, "Broccoli and Carrot Soup",
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Broccoli", 1),
                        new IngredientModel("Carrot", 2),
                        new IngredientModel("Onion", 1))),
                "Boil and blend broccoli, carrot, and onion for a nutritious soup."));

        // Recipe 9: Apple and Walnut Yogurt Parfait
        createRecipe(new RecipeModel(13, "Apple and Walnut Yogurt Parfait",
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Apple", 1),
                        new IngredientModel("Walnut", 1),
                        new IngredientModel("Yogurt", 1))),
                "Layer diced apples, walnuts, and yogurt for a delicious parfait."));

        // Recipe 10: Grilled Chicken Salad with Tomato Dressing
        createRecipe(new RecipeModel(14, "Grilled Chicken Salad with Tomato Dressing",
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Chicken Breast", 2),
                        new IngredientModel("Lettuce", 1),
                        new IngredientModel("Tomato", 1))),
                "Grill chicken, slice, and serve on a bed of lettuce. " +
                        "Top with a homemade tomato dressing for a flavorful salad."));

    }
}
