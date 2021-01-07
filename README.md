# My Personal Project

## User Story
- As a user, I want to be able to add new account to my accounts list.
- As a user, I want to be able to view the list of accounts on my accounts list.
- As a user, I want to be able to have a passcode prior accessing the accounts list.
- As a user, I want to be able to delete an account from my accounts list.
- As a user, I want to be able to save a password and username and/or email to an account.
- As a user, I want to be able to optionally save my account list to file.
- As a user, I want to be able to automatically load my account list from the file when the program starts.
- As a user, I want to be able to optionally save my passcode to file.
- As a user, I want to be able to automatically load my passcode from the file when the program starts.

## Password Manager

- **What will the application do?**

The app helps to store email, usernames and passwords for any account, such as Facebook, Gmail, UBC SSC, etc.
When first opened, the app will require a 4 digit pass code for security reasons (first time users will be 0000). 
If the password is incorrect, it will let the user continue further and just replies "incorrect pass code. try again."
If the password is correct, it will lead the user into a page which contains all accounts. 

- **Who will use it?**
Users who have troubling remembering passwords and usernames for accounts.

- **Why is this project of interest to you?**
This project is interesting to me, because I have struggled with remembering passwords for as long as I can remember.
Currently, I am using a sticky note on my laptop, writing down all the passwords and username/emails, I have used when
creating the account. This 'sticky note' method is unsafe as anyone can access it when they're using my laptop.
As such, by creating this project, I will be able to solve my own problems and be able to learn Java language, applying 
the knowledge learned in class. This would also be my first Java project I will be creating, so I am really looking 
forward to it.

## Instructors Grader
- You can generate the first required event by choosing the 'add' tab and enter according details to add an account
- You can generate the second required event by choosing the 'delete' tab and delete an account from list of accounts
- You can trigger my audio component by logging into the accounts page
- You can save the state of my application by the save tab and click the save accounts/passcode button
- You can reload the state of my application by running the app, it loads the saved data automatically.

## Phase 4:

### Task 2: LoginPassCode class is made robust. 
Passcode should only be able to accept 4-digit numbers such as 1234 and 9999, and passcode is saved as a string. In the
LoginPassCode class, the method validPassCode is created to verify in the input satisfy the characteristics of a 
passcode: made of only numbers and string is only 4 character long. When an incorrect input entered by user,
the changePasscode method will throw the InvalidPassCodeException and deal with it, else the passcode should be changed. 
 
### Task 3:
For the PasswordManagerGUI class, I noticed that there is low cohesion as it was trying to manage the two frame in one
class. For this reason, I split the class into two: LoginPageGUI and PasswordManagerAppGUI
One to handle the login passcode frame, and the other to handle the account frame. 

I also noticed that inside PasswordManagerAppGUI, high cohesion is has not achieved. The class has to create all the
tabs for the password manager app and including the functionality. To increase cohesion, I made an abstract class named
tab, and a new class for each tab to extend the tab, such that each class is only responsible to create the tab and deal 
with the functionality. 