# FindMyRecipes

Receive recipe recommendations based on the ingredients you have at home!

## Partners

- Allen Quizon
- Dan Kim
- Katelyn Valete

## Program Description

This application generates a list of recipes that the user can cook based on the ingredients that the user has. The collection of ingredients used in our algorithm is created via the user's interaction with the application (e.g. the user adding ingredients from an ingredient catalog into the fridge or removing ingredients from the fridge). After the list of recommended recipes is generated and displayed, the user may add selected recipes into a collection of favorited recipes. When exiting the application, the user's collection of ingredients and favorite recipes are saved and loaded when the user starts the application again.

## Running the Project

The necessary files to run the project can be grabbed from the github as a zipfile. An installation of javafx may also be necessary, but otherwise all dependencies and files will be within the zip. From the terminal console, the user should run the following command to export the javafx installation.

```console
export JAVAFX_HOME=/Users/username/PATH_TO_JAVAFX
```

Simply replace the username field with the user name of the device.

Next compile all necessary files and run the program by using these commands:

```console
javac --module-path $JAVAFX_HOME --add-modules javafx.controls,javafx.fxml *.java
java --module-path $JAVAFX_HOME --add-modules javafx.controls,javafx.fxml Main
```

Ensure the current working directory is set to the folder FindMyRecipes.

## Layout and Visual Elements

The main window of our application contains an ingredient catalog and the user's fridge/pantry which is initially empty. The user adds ingredients by selecting them from the catalog which contains 6 food categories. The bottom of the window contains 3 buttons that initiate unique functionality. The design of the main window is shown in Figure 1 below.

<p align="center">
   <i>Figure 1: Catalog/Fridge Window</i> <br />
   <img src="https://github.com/aquizon/FindMyRecipes/blob/main/images/FindMyRecipes%20Mockups/FindMyRecipes%20Mockups.001.jpeg" width="500">
</p>

There is a search bar above the ingredient catalog for the user to easily find an ingredient in the catalog. Searching for an ingredient or clicking on one of the 6 food categories updates the main window to show the ingredients within the selected food category. For instance, clicking on the "Fruits" category displays all the ingredients that are labeled as fruits as shown in Figure 2.

<p align="center">
   <i>Figure 2: Selecting "Fruits" on the Main Window</i> <br />
   <img src="https://github.com/aquizon/FindMyRecipes/blob/main/images/FindMyRecipes%20Mockups/FindMyRecipes%20Mockups.002.jpeg" width="500">
</p>

Clicking the Generate Recipes button switches windows to display a list of recipes that the user can cook based on the ingredients within the user's fridge. Selecting one of the recipes reveals the recipe information, and clicking the heart adds the recipe to the user's list of favorite recipes. Refer to Figure 3 below.

<p align="center">
   <i>Figure 3: Generate Recipes Window</i> <br />
   <img src="https://github.com/aquizon/FindMyRecipes/blob/main/images/FindMyRecipes%20Mockups/FindMyRecipes%20Mockups.003.jpeg" width="500">
</p>

Clicking on the Favorite Recipes button switches to a window with an identical layout to the Generate Recipes window, however only the recipes that the user favorited are displayed as shown in Figure 4. The user can remove a recipe from the favorites list by clicking the heart. The Back to Fridge button takes the user back to the main window.

<p align="center">
   <i>Figure 4: Favorite Recipes Window</i> <br />
   <img src="https://github.com/aquizon/FindMyRecipes/blob/main/images/FindMyRecipes%20Mockups/FindMyRecipes%20Mockups.004.jpeg" width="500">
</p>

Clicking the Save and Quit button stores the user's fridge and favorited recipes in an external file which can be used when the user opens the application again.

## Objectives: Challenges/Modifications/Progress

We came across challenges in our implementation that led us to modify our approach to a few of our objectives. Below is our original list of objectives and our modifications/realizations:

1. Using public datasets to populate our database with recipes, ingredients, and images (backend)

   - Challenges: While we found large datasets we could use for our project, we decided to instead work with a smaller dataset that we manually created because our priority for this project is to have a proof of concept that we can scale in the long term.

2. Implementing the algorithm that generates a list of recipes the user can cook for the main functionality of the application (backend)

3. Displaying the images corresponding to the correct recipes and ingredients on the windows

   - Challenges: With the method we chose to handle the images, it costs a significant amount of time to load each image and then assign them to an ingredient or recipe class instance. Further modifications may be helpful especially if the dataset were to grow larger.

4. Adding a favorite recipes feature that uses a heart-shaped button for the user to easily add/remove recipes from the favorites list

   - Challenges: Despite multiple efforts to sync the state of the heart button between the Generated Recipes scene and the Favorited scene, we were still unable to get it to be fully functional without bugs. Although it seems to work fine when only a single recipe is favorited once additional recipes are added to the favorites, removing it from the favorites list has various unintentional interactions.

5. Displaying a selected recipe's information with good design for the user's readability

6. Switching scenes/part of a scene based on the user's interaction with the application

7. Implementing a search bar for the user to quickly find ingredients

8. Adding a pop-up that indicates whether a user's action was successfully done

   - Modifications: Instead of using a pop-up, we used counts for each of the food categories that updates as the user adds/removes ingredients to/from the fridge.

9. Saving and loading the user's fridge and favorites list to populate TableViews when the user opens the application again

10. Styling the TableView objects with a CSS file

## Technical Details and Decisions

The implementation of our application involves the creation of multiple classes and information/images found online. The features of our technical design and the description of our process are listed below.

### Recipe.java

The recipe class has various variables.

- <ins>**Recipe ID**</ins> - A unique identifier token for each Recipe. This is not displayed directly displayed and instead used to make sorting through the recipes easier.
- <ins>**Recipe Name**</ins> - Self explanatory but is the name of the recipe to be displayed in the tableviews.
- <ins>**Ingredients with the quantities**</ins> - this is used in order to be displayed on the information screen when a recipe is selected in the favorites or generated recipes scenes. Stored as an arraylist with each value being an individual ingredient.
- <ins>**Ingredients without the quantities**</ins> - This is not displayed but instead used in the algorithm to generate applicable recipes based upon the ingredients in the fridge. Also an arraylist with each value being an ingredient.
- <ins>**Recipe Instructions**</ins> - Stored as an arraylist, each step of the recipe is a value in the arraylist. Displayed in the information screens.
- <ins>**Favorited**</ins> - A boolean that stores if the recipe was favorited or not.
- <ins>**HeartButton**</ins> - Each recipe will have a custom heartbutton which state is given by the favorited boolean.

### Ingredient.java

This class contains the attributes for a single ingredient:

- <ins>**ID**</ins> - Similar to the ID for each recipe, each ingredient also has a unqiue identified token that is not displayed directly.
- <ins>**Name**</ins> - All ingredients have a name associated to them. This can be used to search for an ingredient in the CatalogFridge scene.
- <ins>**Category**</ins> - The ingredients are sorted into certain food categories. These being "Fruits", "Vegetables", "Grains", "Proteins", "Dairy", and "Other".
- <ins>**ImageView 1**</ins> - The first imageview associated with an ingredient is used to display an image of the ingredient to the ingredient catalog.
- <ins>**ImageView 2**</ins> - An identical imageview to the first one. I am under the belief that a single imageview can only be displayed at one place at a time so the second is used to display an image when the ingredient is added to the fridge.

### RecipeData.java

This class parses through a CSV containing information on 30 hand-picked recipes from Allrecipes and creates a HashMap that stores a recipe name as the key and a Recipe object as the value. Below is a sample of the spreadsheet our group created that, when exported as a CSV, uses the pipe ("|") as a delimiter, as well as a sample of the CSV.

<p align="center">
   <i>Figure 5: RecipeDataset Sample</i> <br />
   <img src="https://github.com/aquizon/FindMyRecipes/blob/main/images/RecipeDatasetSample.png" width="700">
</p>

```
ID|Title|List of Ingredients|Instructions
1|Creamy Pesto Shrimp|['1 pound linguine pasta', '1/2 cup butter', '2 cups heavy cream', '1/2 teaspoon ground black pepper', '1 cup grated Parmesan cheese', '1/3 cup pesto', '1 pound large shrimp--peeled and deveined']|['Gather ingredients together.', 'Fill a large pot with lightly salted water and bring to a rolling boil. Stir in linguine and return to a boil. Cook pasta uncovered, stirring occasionally, until tender yet firm to the bite, about 8 to 10 minutes; drain.', 'Meanwhile, melt butter in a large skillet over medium heat. Stir in cream and season with pepper; cook, stirring constantly, for 6 to 8 minutes.', 'Stir Parmesan cheese into cream sauce until thoroughly mixed. Stir in pesto and cook until thickened, 3 to 5 minutes.', 'Stir in shrimp and cook until they turn pink, about 5 minutes. Serve sauce over hot linguine.']|./images/recipes/recipe1.png
2|Lemon Herb Chicken|['2 (5 ounce) chicken breasts--skinless, boneless, halves', '1 medium lemon', 'salt', '1 tablespoon olive oil', '1 pinch dried oregano', 'ground black pepper', '2 sprigs fresh parsley--for garnish’]|['Place chicken in a bowl; pour 1/2 of the lemon juice over top and season with salt', 'Heat oil in a small skillet over medium-low heat. Add chicken to the hot oil, along with the remaining lemon juice and oregano. Season with pepper. Sauté until golden brown and the juices run clear, 5 to 10 minutes per side.',  'Serve with parsley for garnish.']|./images/recipes/recipe2.png

```

A helper function is used to print all of the ingredients listed in the spreadsheet that will be used to populate the ingredient catalog in the main window. This list of ingredients is stored in a separate spreadsheet where we manually added the categories and image filenames as shown in Figure 6.

<p align="center">
   <i>Figure 6: RecipeIngredientsDataset Sample</i> <br />
   <img src="https://github.com/aquizon/FindMyRecipes/blob/main/images/RecipeIngredientsDataset.png" width="700">
</p>   
  
### CatalogFridge.java

A class containing the stage and scene of the Catalog/Fridge window.

The Catalog Fridge is constructed using a GridPane. The points of interest are the search box, food categories, tableviews and the bottom menu bar.

Within the search bar once the user presses enter the scene will replace the food categories or tableview with a filtered tableview that matches the search criteria.

The food categories is comprised of 6 different buttons that will redirect to a table that contains all the ingredients within that food category. The right most table is the "fridge" that stores all the added ingredients.

The tableviews for the food categories are built using two columns. The first column is just an image of the ingredient while the second column is a vBox that has the name of the ingredient and a button to either add or remove the ingredient to/from the fridge.

The bottom menu bar are three buttons that will redirect the user to either the favorite recipes, generated recipes scene and the save and quit button.

<p align="center">
   <i>Figure 7: Catalog Fridge Display</i> <br />
   <img src="https://github.com/aquizon/FindMyRecipes/blob/main/images/FindMyRecipes%20Progress%20Images/CatalogFridge.png" width="700">
</p>

### GeneratedRecipes.java

A class containing the stage and scene of the Generated Recipes window.

The Generated Recipes is simpler with just one tableview on the left side and a information display on the right also featuring the menu bar.

If all of the ingredients in a recipes ingredient list is within the fridge, that recipe will be displayed in the tableview. Each recipe also has a heart button, if that button is clicked the heart will fill up and the recipe can also be found in the favorite recipes.

Upon clicking a recipe in the table its details will be shown to the right in an informative menu. The image of the recipe, its name, instructions and ingredients are all listed in this menu.

The bottom menu is nearly identical to the menu bar in Catalog Fridge except the generated recipes button is replaced with a button that returns to the Catalog Fridge scene.

<p align="center"> 
   <i>Figure 8: Generated Recipes Display</i> <br /> 
   <img src="https://github.com/aquizon/FindMyRecipes/blob/main/images/FindMyRecipes%20Progress%20Images/GeneratedRecipes.png" width="700">

### Favorites.java

A class containing the stage and scene of the Favorite Recipes window.

The favorites scene is nearly identical to the Generated Recipes scene also having a tableview, information display and the menu bar.

This tableview is the same as the Generated Recipes table. Only recipes that have been favorited in the gernerated recipes table is shown.

Upon clicking a recipe, its etails will also be displayed to the right hand side.

The menu bar here is also the same. In comparison to the menu bar in the Generated Recipes scene, the Favorites button is replaced with a Generated Recipes button to redirect to the Generated Recipes scene.

<p align="center"> 
   <i>Figure 9: Favorite Recipes Display</i> <br /> 
   <img src="https://github.com/aquizon/FindMyRecipes/blob/main/images/FindMyRecipes%20Progress%20Images/Favorites.png" width="700">

### FavoritesList.java

A class containing an ObservableList of Recipe objects used in Favorites.java, GeneratedRecipes.java, and Main.java. An instance of FavoritesList is passed between these classes to ensure that the same list of favorited recipes are used in different scenes.

### FindMyRecipesFileHandler.java

Contains methods that save and load the user's fridge and favorite recipes. Main.java uses the returned values from the load method to update the structures that represent the user's saved information.

## Reflections

## References

- [Search bar implementation](https://edencoding.com/search-bar-dynamic-filtering/)

- [Adding buttons to a TableView](https://stackoverflow.com/questions/29489366/how-to-add-button-in-javafx-table-view)

- [Allrecipes (source used to create our small dataset)](https://www.allrecipes.com/)

- [Adding an image to a button](https://www.tutorialspoint.com/how-to-add-an-image-to-a-button-action-in-javafx)

- [Custom shaped buttons](https://genuinecoder.com/javafx-buttons-with-custom-shape/#:~:text=JavaFX%20allows%20setting%20custom%20shapes,by%20giving%20proper%20SVG%20path.)

- [Table Styling] - (https://gist.github.com/kinsleykajiva/ac452bc8280bc52be384e9af98f2ba0c)
   
- [Adding an image to the table] - (https://stackoverflow.com/questions/22844042/inserting-images-into-a-tableview-javafx-images-not-displayed)
   
- [Color References] - (https://www.canva.com/colors/color-wheel/)
