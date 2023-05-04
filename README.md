# FindMyRecipes
Receive recipe recommendations based on the ingredients you have at home!

## Partners
- Allen Quizon
- Dan Kim
- Katelyn Valete

## Program Function
This application generates a list of recipes that the user can cook based on the ingredients that the user has. The collection of ingredients used in our algorithm is created via the user's interaction with the application (e.g. the user adding ingredients from an ingredient catalog into the fridge or removing ingredients from the fridge). After the list of recommended recipes is generated and displayed, the user may add selected recipes into a collection of favorited recipes. When exiting the application, the user's collection of ingredients and favorite recipes are saved and loaded when the user starts the application again.

## Layout
The main window of our application contains an ingredient catalog and the user's fridge/pantry which is initially empty. The user adds ingredients by selecting them from the catalog which contains 6 food categories. The bottom of the window contains 3 buttons that initiate unique functionality


The main window of our application contains an ingredient catalog and the user's fridge/pantry. Note that when the user first opens the application, the fridge is empty. The user populates the fridge/pantry by selecting ingredients from the ingredient catalog which has 6 food categories. The bottom of the window contains 3 buttons that initiate unique functionality and/or switch windows. 

Figure 1 below represents the main window that displays the ingredient catalog on the left, the user's fridge on the right, and a set of buttons on the bottom of the window that, upon clicking, initiate other functionality and/or navigate to other windows. Note that when the user first opens the application, the fridge will be empty. When it is later populated, it will look like the image shown in Figure 1.

Figure 1: Catalog/Fridge Window
![CatalogFridgeMockUp](https://user-images.githubusercontent.com/90003553/236037240-f1cdff9d-ff88-49f0-bca3-f208745056ee.jpeg)

There is a search bar above the ingredient catalog for the user to easily find an ingredient in the catalog. Searching for an ingredient or clicking on one of the 6 food categories updates the main window to show the ingredients within the selected food category. For instance, clicking on the "Fruits" category displays all the ingredients that are labeled as fruits as shown in Figure 2.

Figure 2: Selecting "Fruits" on the Main Window
![SelectedCategoryMockUp](https://user-images.githubusercontent.com/90003553/236040726-66d75dd0-c01f-4359-944e-02b1a2c05c70.jpeg)



## Objectives
