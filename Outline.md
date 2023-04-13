# Project 02: Find My Recipes

## Partners
* Katelyn Valete
* Allen Quizon

## Program Function

The main purpose of the application is to generate a list of recipes that a user can cook based on a set of ingredients selected by the user which reflects the ingredients they have in their “fridge” or home. From that generated list of recipes, the user can select recipes to save in a collection of “favorite recipes” which can be modified (e.g. the user can later un-favorite the recipe, removing it from the “favorite recipes” collection). The recipes and ingredients are extracted from public datasets.

## Main Visual Elements
The catalog/fridge window contains the following elements:
1. A container occupying the left half of the window that houses the catalog of all possible ingredients the user can add organized in 4 alphabetical groups (e.g. A-E, F-K, L-P, Q-Z). Selecting one of the alphabetical categories changes the contents of this container such that it displays an indexed table of ingredients within the selected category (e.g. all ingredients A-E). Within this container is a button to return back to the main catalog content.
2. A search bar (using a TextField and Button) above the aforementioned container that allows the user to quickly find ingredients.
3. A ListView occupying the right half of the window that contains the ingredients the user added from the catalog. 
4. A menu bar located at the bottom of the this window containing three buttons: “Favorite Recipes,” “Generate Recipes,” and “Save and Exit.”

The generated recipes window contains the following elements:
1. This window will have a TableView/ListView containing the list of recipes generated from the ingredients in the fridge
    * The user will be able to favorite and select recipes for details on each row of the TableView/ListView
2. The window will also contain a recipe detail outline (name, instructions, ingredients, etc.) within a Vbox containing Hboxes
3. The bottom of the window will also have the same menu bar as the catalog window, except that it has a “back to fridge” option that replaces the “generate recipes” option, so it would keep the favorites option and the save and exit option

The favorites window contains the following elements:
1. Clicking the “Favorite Recipes” button on the menu bar opens a window similarly formatted like the Generated recipes window (containing a TableView/ListView of favorited recipes)
2. The menu bar will be the same as the Generated recipes window (“Favorite Recipes,” “Back to Fridge,” and “Save and Exit”)

## Technical Outline
* Reading in items from an online database
* Constructing proper visual data structures for visual elements
    * Example: Tableview for Ingredient Catalog Index, custom button shape for “favorite” heart
* Constructing a search bar algorithm
* Adding images to corresponding ingredients and recipes
* Changing the window/scene on button click

## Technical References
* URL for Food Ingredients and Recipes with Images on Kaggle:
    * https://www.kaggle.com/datasets/pes12017000148/food-ingredients-and-recipe-dataset-with-images
* URL for Ingredients and corresponding images from CROCOFID:
    * https://osf.io/5jtqx/
* Custom Heart Shaped Button:
    * https://genuinecoder.com/javafx-buttons-with-custom-shape/
* Adding Buttons to TableView:
    * https://stackoverflow.com/questions/29489366/how-to-add-button-in-javafx-table-view
* Changing scenes in the same window
    * https://www.youtube.com/watch?v=hcM-R-YOKkQ&ab_channel=BroCode

* Objectives
1. Using public datasets to populate our database with recipes, their ingredients, and images of recipes and ingredients (backend)
2. Generating a list of recipes based on a set of ingredients added by the user (backend)
    * Add boolean canCook(List<Ingredient> fridge) to the recipe class which takes a fridge/list of ingredients and returns true if you can make that recipe given the fridge and false if not
    * Add List<Recipe> generateRecipes(List<Ingredient> fridge) to the RecipeList class which parses through the recipe list and returns a list of recipes that can be generated using the fridge ingredient list and the canCook method
3. Adding images of ingredients/recipes to a TableView or ListView of hboxes for easier recognition / a better visual experience
    * Adding columns to the Catalog table which mirrors the style of the fridge except it has two columns (2 columns and non-resizable for now) 
4. Adding a “Favorites” feature to allow the user to save/unsave arbitrary recipes by clicking an image of (for example) a heart
    * Bezier Curves might be used to outline the heart-shaped button
5. Displaying the recipe’s image and information when selected and formatting it nicely for the user’s readability 
    * Applies to both the generated recipes and the favorite recipes windows
6. Changing the scene depending on the mode (e.g. when the user clicks a category on the catalog, replace that part of the scene with the scene containing ingredients within that category)
    * Add a menu bar at the bottom of the window that updates on the mode (e.g. if the user views the fridge, the menu bar includes a button to generate recipes which is replaced by a button to return to the fridge when the user views the generated recipes)
    * Add a back button to switch to the previous scene
7. Search bar for ingredients
    * Search by if the items have the searched item name in it
        * Example: If the search was “strawberry”, “strawberry” and “strawberry yogurt” would pop up
8. Add pop-up image to the catalog/fridge scene, which says whether an ingredient is successfully added to a fridge or not (may be animated)

## Mock-up Pictures
Home/Catalog Page:

![alt text](home_page.jpeg)

Generate Recipes Page:  

![alt text](generate_recipes.jpeg)

Favorites Page:

![alt text](favorites_window.jpeg)