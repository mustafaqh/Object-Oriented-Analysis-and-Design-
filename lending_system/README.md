# A2

# Group members:
* Shaimaa Bakir
* Marah Awad
* Mustafa Habeb

# About our Stuff Lending System:
When you run the application you will get a main menu with diffirent choices. If you want to advance time you enter (time). If you want to add a new member you enter (add) and the you should enter the new members information, after that the member will be registerd. If you want to delete a member press (delete) and the you will get a list of all members to choose between. All you have to do is to enter the id of the member that you want to delte and then this member will be deleted from the list. Same for changing a members info, you need first to choose a member then you will get some options of what do you want to edit for ex first name etc. If you want to list all the members in a simple way just press list s. Same for list members in a verbose way, just press list v. To show all items in the app enter items, and you will get list of all items with info. If you press Item, you will first select a member then you will get a menu for item. In this menu you can add an item for this member that you selected, lend an item from other members, delete an item, change info of an item, view all items for this member, and view a specific item full info for this member.




Project for Assignment 2

A project template based on gradle and a gitlab pipeline. You should always build and run the application using gradle regularely.

[design.md](design.md) contains the prescribed architectural design of the application.

## Building
The build must pass by running console command:  
`./gradlew build`  
Note that you should get a report over the quality like:
```
CodeQualityTests > codeQuality() STANDARD_OUT
    0 CheckStyle Issues in controller/App.java
    0 CheckStyle Issues in controller/Simple.java
    0 CheckStyle Issues in model/Simple.java
    0 CheckStyle Issues in view/Simple.java
    0 FindBugs Issues in controller/App.java
    0 FindBugs Issues in model/Simple.java
    0 FindBugs Issues in view/Simple.java
    0 FindBugs Issues in controller/Simple.java
```

Removing or manipulating the code quality checks results in an immediate assignment **Fail**. 

## Running
The application should start by running console command:  
`./gradlew run -q --console=plain`

## Adding Your Own Code
The `Simple` classes project should likely be removed do not forget to also remove the test case associated to `model.Simple`.  

Add your own code to the packages respectively and feel free to add automatic test cases for your own code. A good process is to design a little - code a little - test a little one feature at a time and then iterate.

## Versioning

Adhere to the git versioning instructions according to the assignment.

## System test
Adhere to the instructions according to the assigment.

## Handing In
Adhere to the instructions according to the assigment.