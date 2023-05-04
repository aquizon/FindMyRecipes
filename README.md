# FindMyRecipes
Receive recipe recommendations based on the ingredients you have at home!

## Partners
- Allen Quizon
- Dan Kim
- Katelyn Valete

## Program Description
This application generates a list of recipes that the user can cook based on the ingredients that the user has. The collection of ingredients used in our algorithm is created via the user's interaction with the application (e.g. the user adding ingredients from an ingredient catalog into the fridge or removing ingredients from the fridge). After the list of recommended recipes is generated and displayed, the user may add selected recipes into a collection of favorited recipes. When exiting the application, the user's collection of ingredients and favorite recipes are saved and loaded when the user starts the application again.

## Layout and Visual Elements
The main window of our application contains an ingredient catalog and the user's fridge/pantry which is initially empty. The user adds ingredients by selecting them from the catalog which contains 6 food categories. The bottom of the window contains 3 buttons that initiate unique functionality. The design of the main window is shown in Figure 1 below.

Figure 1: Catalog/Fridge Window
![CatalogFridgeMockUp](https://user-images.githubusercontent.com/90003553/236037240-f1cdff9d-ff88-49f0-bca3-f208745056ee.jpeg)

There is a search bar above the ingredient catalog for the user to easily find an ingredient in the catalog. Searching for an ingredient or clicking on one of the 6 food categories updates the main window to show the ingredients within the selected food category. For instance, clicking on the "Fruits" category displays all the ingredients that are labeled as fruits as shown in Figure 2.

Figure 2: Selecting "Fruits" on the Main Window
![SelectedCategoryMockUp](https://user-images.githubusercontent.com/90003553/236040726-66d75dd0-c01f-4359-944e-02b1a2c05c70.jpeg)

Clicking the Generate Recipes button switches windows to display a list of recipes that the user can cook based on the ingredients within the user's fridge. Selecting one of the recipes reveals the recipe information, and clicking the heart adds the recipe to the user's list of favorite recipes. Refer to Figure 3 below.

Figure 3: Generate Recipes Window
![GeneratedRecipesMockUp](https://user-images.githubusercontent.com/90003553/236274601-5b49cdea-1e05-4b84-b1af-f42363d0db36.jpeg)

Clicking on the Favorite Recipes button switches to a window with an identical layout to the Generate Recipes window, however only the recipes that the user favorited are displayed as shown in Figure 4. The user can remove a recipe from the favorites list by clicking the heart. The Back to Fridge button takes the user back to the main window.

Figure 4: Favorite Recipes Window
![FavoriteRecipesMockUp](https://user-images.githubusercontent.com/90003553/236275226-b6e71af1-51b4-46a4-838b-19c2fa97a5bf.jpeg)

Clicking the Save and Quit button stores the user's fridge and favorited recipes in an external file which can be used when the user opens the application again.

## Technical Details

## Objectives: Challenges/Modifications/Progress
We came across challenges in our implementation that led us to modify our approach to a few of our objectives. Below is our original list of objectives and our modifications/realizations:

1. Using public datasets to populate our database with recipes, ingredients, and images
    * Modifications/realizations: While we found large datasets we could use for our project, we decided to instead work with a smaller dataset that we manually created because our priority for this project is to have a proof of concept that we can scale in the long term.

2. Implementing the algorithm that generates a list of recipes the user can cook for the main functionality of the application

3. Displaying the images corresponding to the correct recipes and ingredients on the windows

4. Adding a favorite recipes feature that uses a heart-shaped button for the user to easily add/remove recipes from the favorites list

5. Displaying a selected recipe's information with good design for the user's readability

6. Switching scenes/part of a scene based on the user's interaction with the application

7. Implementing a search bar for the user to quickly find ingredients

8. Adding a pop-up that indicates whether a user's action was successfully done
