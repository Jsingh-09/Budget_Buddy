#  Budget Buddy <img src="https://i.imgur.com/oM2Jsl2.png" width="60" height="60">

## About Budget Buddy
Budget Buddy is a budgeting app for students to create a habit of balancing their funds and ensuring that they can make ends meet.


## Background
According to “4 Common Financial Issues for College Students” by Charter College, college students misuse student loan money, fail to stick to a realistic budget, and build up credit card debt. Since we are not taught how to manage our money, we come across significant issues that can potentially impact our academics and our ability to afford basic needs. The lack of financial education creates a problem that exists among college students. An efficient way to solve it is to create a mobile application that helps students manage their money.

## Proposed Solution
Budget Buddy will help students budget their finances to cover all of their expenses, better manage their income, and efficiently allocate their resources. It also includes a budget calculator that would consider the number of costs, financial aid, and revenue. This budget calculator will give the student a rough estimate of their disposable income after expenditures. The application suggests allocating those funds to cover all expenses and, if possible, also allow the student to end with a net positive. The application would also provide tips and resources to help them better budget themselves, save money towards a goal(car/apartment), and build credit. Thus, assisting students in developing better budgeting habits.through this web app.


## Running the Application

To run the application, clone the repo and open the whole folder using Android Studio. Note, API keys will not be accessible once the project is deployed.

## Early Demo
<img src='https://recordit.co/QWbC9yJ7Bi.gif' title='Video Walkthrough' width='250' alt='Video Walkthrough1'>
Demo as of 11/16/2020

## Latest Demo
<img src='https://imgur.com/G44b5wa.gif' title='Video Walkthrough' width='250' alt='Video Walkthrough1'>
Demo as of 12/11/2020

## [Back4App and Parse](https://www.back4app.com/docs/get-started/welcome)
- Low-code backend
- Parse compatibility for Android Studio

## Authors
- [Jashandeep Singh](https://www.linkedin.com/in/-jashandeep-singh/), [Chris Gallo](https://www.linkedin.com/in/chrisgallo17/), [Natasha Garcia](https://www.linkedin.com/in/natasha-g-0451441b3/), [Stephanie Gamboa](https://www.linkedin.com/in/stephanie-gamboa-/), [Jorge Hernandez](https://www.linkedin.com/in/jorge-hernandez-ortega-77486418b/)

## Classes & Files Explained:

- AppParse: This class connects the app with the correct database using the Keys.

- Budget_Cacluator: This class is the main activity for Budget Calculator. It takes in the user’s account balance, income, and average expenses, then calculates them to let the user know if they are within or over their budget.

- BuildingCredit: This is the main activity for Building Credit. It stores and displays information on how to build credit.

- BuildingCredit2: This is the second page that is called when the user clicks on any of the pictures for each section of information. It shows them the same information in that section in one page, that way it makes it easy for the user to read and scroll through. 

- CreditAdapter: This is used in order for the information to be displayed as a RecyclerView for the BuildingCredit Activity. This takes in the information and defines items that are on the recycler view. 

- CreditModel: This activity is related to Building Credit and it is used for getting and setting the information from the main BuildingCredit Activity. 

- CreditViewHolder: This activity was created in order to define the items for Building Credit Activity.  It defines the images and textviews of the item_credit xml file.

- Expense: This class holds the objects from the expense part of the database.

- ExpenseAdapter: This is used in order for the information to be displayed as a RecyclerView for the ListItemsFragment. This takes in the information and defines items that are on the recycler view. 

- GoalsAdapter: This is used in order for the information to be displayed as a RecyclerView for the ProgressFragment. This takes in the information and defines items that are on the recycler view. 

- LoginActivity: This is an activity which allows the user to login to the app. It uses the email address and the password that the user uses to login in so that the user can use the app.

- ProfileActivity: This is the home page of the app that allows users to navigate between the different features that are in the app. It displays the user name and email address at the top and four icons in the middle that are connected to the different activities.

- Reset PasswordActivity: This activity allows the user to reset their password if the user forgets their password while signing. The user will get an email so that they can reset their password by following the instructions in the email.

- Saving: This class hold the objects from the “Saving” class which is in our Parse database

- SavingGoalActivity: This class is an actively which holds the code for the bottom navigation and allows the user to switch between fragments. 

- SignupActivity:  This class allows the user to create a new user in the database with the given attributes.

- AddFragment: This class is a fragment and allows the user to input data. The data is then saved into our database.

- AddGoalFragment: This class adds a goal to the database with the given attributes.

- GraphFragment: This class displays a graph of queried data based on the Expense class.

- ListItemsFragment: This class is a fragment that queries the expenses from the database and then displays it to the user.

- ProgressFragment: Displays all of the saving objects in the database assigned to the current user.

- UpdateFragment: This class queries a specific saving object in the database and modifies it, based on user input, then saves it to the database.

- Track_Expenses: This class is an actively which holds the code for the bottom navigation and allows the user to switch between fragments. 


## How To Use The App 

- Sign Up using your email address
- Sign in with user credentials
- Navigate through the app by clicking the different icons: 
- Building Credit: Search feature allows string input
- Budget Calculator: Input your current account balance, average expenses, and average income to calculate your budget.
- Tracking My Expense
 - Adding Expense: Fill in location, select item category, fill in the amount spent, and select a data
 - List of Expenses: Lists the users personal expenses. Swipe right on an expense to delete
 - Graph: Displays the data from expenses as a graph, allowing the user to see the totals spent in each category.
- Saving Goals:
 - Add Goal: Fill in Goal name, give a short description of the goal, select the goal category, fill in the goal amount, and how much is already saved, then press add goal.
 - Update Goals: Select which goal you want to update, then fill out how much you want to add then press update goal.
 - Progress: Lists the user’s personal goals and allows them to be deleted by swiping right on the goal.
- Sign out: signs out the current user and returns them to the LoginActivity




## Unit Tests 

- Budget Calculator
a. Input Data “100” “50.1.0” “50”
b. Expected Outcome: Fail, too many decimals
c. Actual Outcome: Fail, too many decimals
d. Comparison: Same Result
e. Input Data “100” “.” “20”
f. Expected Outcome: Fail, invalid input
g. Actual Outcome: Fail, invalid input
h. Comparison: Same Result
i. Input Data “500” “100” “200”
j. Expected Outcome: 600
k. Actual Outcome: 600
l. Comparison: Same Result


- Track My Expenses - Add Expense 
a. Input Data: “Walmart” “Groceries” “12….” “12/09/2020”
b. Expected Outcome: Fail, too many decimals 
c. Actual Outcome:Fail, too many decimals
d. Comparison: Same Result
e. Input Data: “” “Fast Food” “15.50” “12/11/2020”
f. Expected Outcome: Fail, Fields cannot be empty
g. Actual OutcomeFail, Fields cannot be empty
h. Comparison: Same Result
i. Input Data: “Walmart” “Groceries” “15.555555555” “12/09/2002”
j. Expected Outcome: Accept. Expense add ( “Walmart” “Groceries” “15.56” “12/09/2002”)
k. Actual Outcome: Accept. Expense add ( “Walmart” “Groceries” “15.56” “12/09/2002”)
l. Comparison: Same Result

- Sign In
a. Input Data: “jashanbhinder2@gmail.com” “12345”
b. Expected Outcome: Pass, email address and the password matches
c. Actual Outcome: Pass, email address and the password matches
d. Comparison: Same Result

- Reset Password
a. Input Data: “jashan@gmail.com”
b. Expected Outcome: fail, invalid email address
c. Actual Outcome: fail, invalid email address
d. Comparison: Same Result

- Sign up
a. Input Data: “Corey” “Corey123@gmail.com” “12345” “1234”
b. Expected Outcome: fail, Password are not the same
c. Actual Outcome: fail, Password are not the same
d. Comparison: Same Result


- Saving Goal - Add Goal 
a. Input Data: “Vacation”, “Trip to New York”, “$3,000”, “0...21”
b. Expected Outcome: Fail, “Too many decimals” 
c. Actual Outcome: Fail, “Too many decimals” 
d. Comparison: Same Results 
e. Input Data: “_______” , “Trip to New York”, “3,000”, “0.21”
f. Expected Outcome: Fail, “Fields cannot be Empty”
g. Actual Outcome: Fail, “Fields cannot be Empty”
h. Comparison: Same Results 
i. Input Data: “Vacation”, “Trip to New York”, “$3,000”, “0.21”
j. Expected Outcome: Success, “Added to Goals”
k. Actual Outcome:  Success, “Added to Goals”
l. Comparison: Same Results 
