# manageYourBudget
Application helping in managing personal budget


<h2>Technologies used:</h2>

- Java 
- Spring, Spring Boot
- Spring Security
- H2DB, JPA
- JUnit, Mockito

<h2>Main features:</h2>


<h3>- Budget Change</h3>

It is an object that represents incomes and expenses in the application.
Every budget change should have assigned category which user can freely add,delete or edit.

Incomes and expenses are differentiated by their value - incomes always have positive value and expenses always have negative value.

<h3>- Budget Report</h3>

It is a feature which role is to make reports summarizing incomes and expenses at certain period of time.
Report contains informations about incomes and expenses summarized at every category and as a whole.

Report JSON object example:

![budgetReport](https://user-images.githubusercontent.com/55853764/187045137-6180eb88-93b6-4324-9bd7-a0b54eec7d7e.JPG)


<h3>- Budget Plan</h3>

It allows user to make Budget plan for a certain month which contains informations, how much money user wants to spend on every category.It is possible to make report for plan at any moment to see in what state is fulfilling the plan

Plan report JSON object example:

![planReport](https://user-images.githubusercontent.com/55853764/187046232-e7fa3a53-10b5-4f42-abad-5947c9b46f03.JPG)


