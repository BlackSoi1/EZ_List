# To-Do List 

## "EZ"
The to-do list application is named **EZ**. This application has following *features*: 
- Adding item to the list
- Cross items off
- Showing all  items
- Showing completed items
- Set priorities of items
- Remind user when items in the list approach the deadline
  
 This software is designed for anyone who wants to keep tracking their to-do list, managing 
 their work and reminding them not to miss the deadline. Since the second year in university,
 there are much more assignments and tests. I find it is difficult to remember all the tasks
 in mind. I used fews to-do list applications before. However, in my opinion, these softwares are
 too "powerful". For example, some of them have a news section and some of them can edit the background
 picture of the list. I want to make a simple to-do list application that only do *"to-do list"* work.
 And this is the reason why I named it "EZ".
 

## User Stories
- As a user, I want to be able to add a item to my to-do list.

- As a user, I want to be able to cross items off on my to-do list.

- As a user, I want to be able to view all the tasks on the list.

- As a user, I want to be able to view only the completed tasks on the list.

- As a user, I want to be able to set priorities of tasks on my list.

- As a user, I want to be able to save my to-do list to file.

- As a user, I want to be able to be able to load my to-do list from file 

##"Phase 4: Task 2":     
- Option "Test and design a class in your model package that is robust.  You must have at least one method that throws a checked exception.  You must have one test for the case where the exception is expected and another where the exception is not expected."
- Class: ToDoList
- Method: addTask(),  deleteTask()

##"Phase 4: Task 3":    
If I have more time, I would like to improve the cohesion and lower the coupling.
Especially in the ui package, I will divide the JButtonComponent class into different
class to represent the different button functionalities. Besides, I will create a class named
JPanelComponent class to represent the different windows in the frame. Also I will use
Observer Design pattern to record the task into the to-do List.