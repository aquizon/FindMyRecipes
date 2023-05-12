# FindMyRecipes

Receive recipe recommendations based on the ingredients you have at home!

## Partners

- Allen Quizon
- Dan Kim
- Katelyn Valete

## Program Description

This application generates a list of recipes that the user can cook based on the ingredients that the user has. The collection of ingredients used in our algorithm is created via the user's interaction with the application (e.g. the user adding ingredients from an ingredient catalog into the fridge or removing ingredients from the fridge). After the list of recommended recipes is generated and displayed, the user may add selected recipes into a collection of favorited recipes. When exiting the application, the user's collection of ingredients and favorite recipes are saved and loaded when the user starts the application again.

## Running the Project

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

1. Using public datasets to populate our database with recipes, ingredients, and images

   - Modifications/realizations: While we found large datasets we could use for our project, we decided to instead work with a smaller dataset that we manually created because our priority for this project is to have a proof of concept that we can scale in the long term.

2. Implementing the algorithm that generates a list of recipes the user can cook for the main functionality of the application

3. Displaying the images corresponding to the correct recipes and ingredients on the windows

4. Adding a favorite recipes feature that uses a heart-shaped button for the user to easily add/remove recipes from the favorites list

5. Displaying a selected recipe's information with good design for the user's readability

6. Switching scenes/part of a scene based on the user's interaction with the application

7. Implementing a search bar for the user to quickly find ingredients

8. Adding a pop-up that indicates whether a user's action was successfully done
   - Modifications: Instead of using a pop-up, we used counters for each of the food categories.

## Technical Details and Decisions

The implementation of our application involves the creation of multiple classes and information/images found online. The features of our technical design and the description of our process are listed below.
   
### Recipe.java
The recipe class has various variables. 
* Recipe ID - This is not displayed directly displayed and instead used to make sorting through the recipes easier. 
* Recipe Name - Self explanatory but is the name of the recipe to be displayed in the tableviews.
* Ingredients with the quantities - this is used in order to be displayed on the information screen when a recipe is selected in the favorites or generated recipes scenes. Stored as an arraylist with each value being an individual ingredient.
* Ingredients without the quantities - This is not displayed but instead used in the algorithm to generate applicable recipes based upon the ingredients in the fridge. Also an arraylist with each value being an ingredient. 
* Recipe Instructions - Stored as an arraylist, each step of the recipe is a value in the arraylist. Displayed in the information screens.  
* Favorited - A boolean that stores if the recipe was favorited or not. 
* HeartButton - Each recipe will have a custom heartbutton which state is given by the favorited boolean. 

### Ingredient.java

This class contains the attributes for a single ingredient:

* ID
* Name
* Category
* ImageView 1 (for the catalog)
* ImageView 2 (for the fridge)

### RecipeData.java

This class parses through a CSV containing information on 30 hand-picked recipes from Allrecipes and creates a HashMap that stores a recipe name as the key and a Recipe object as the value. Below is a sample of the spreadsheet our group created that, when exported as a CSV, uses the pipe ("|") as a delimiter.

<p align="center">
   <i>Figure 5: RecipeDataset Sample</i> <br />
   <img src="https://github.com/aquizon/FindMyRecipes/blob/main/images/RecipeDatasetStample.png" width="700">
</p>

```
ID|Title|List of Ingredients|Instructions
1|Creamy Pesto Shrimp|['1 pound linguine pasta', '1/2 cup butter', '2 cups heavy cream', '1/2 teaspoon ground black pepper', '1 cup grated Parmesan cheese', '1/3 cup pesto', '1 pound large shrimp--peeled and deveined']|['Gather ingredients together.', 'Fill a large pot with lightly salted water and bring to a rolling boil. Stir in linguine and return to a boil. Cook pasta uncovered, stirring occasionally, until tender yet firm to the bite, about 8 to 10 minutes; drain.', 'Meanwhile, melt butter in a large skillet over medium heat. Stir in cream and season with pepper; cook, stirring constantly, for 6 to 8 minutes.', 'Stir Parmesan cheese into cream sauce until thoroughly mixed. Stir in pesto and cook until thickened, 3 to 5 minutes.', 'Stir in shrimp and cook until they turn pink, about 5 minutes. Serve sauce over hot linguine.']|./images/recipes/recipe1.png

```

A helper function is used to print all of the ingredients listed in the spreadsheet that will be used to populate the ingredient catalog in the main window. This list of ingredients is stored in a separate spreadsheet where we manually added the categories and image filenames as shown in Figure 6.

Figure 6: RecipeIngredientsDataset Sample
<img width="914" alt="RecipeIngredientsDatasetSample" src="https://user-images.githubusercontent.com/90003553/236321774-933191f5-0cf3-4e16-a1d2-308273af337b.png">

<p align="center">
   <i>Figure 6: RecipeIngredientsDataset Sample</i> <br />
   <img src="https://github.com/aquizon/FindMyRecipes/blob/main/images/RecipeIngredientsDataset.png" width="700">
</p>   
  
### CatalogFridge.java

A class containing the stage and scene of the Catalog/Fridge window.

(add details on data structures used, other attributes)

### GeneratedRecipes.java

A class containing the stage and scene of the Generated Recipes window.

(add details on data structures used, other attributes)

### Favorites.java

A class containing the stage and scene of the Favorite Recipes window.
