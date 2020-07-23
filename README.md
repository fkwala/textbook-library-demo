# Textbook Rental Service 📖

### ***Features*** of the Application ❗ 
This application is a virtual library application that integrates technology into the real-life library services. Just 
like how a library operates, the application has **2** main functions: **Donation** or **Loan**. The application allows students 
to donate their textbooks to the textbook collection. More importantly, the application also allows students to search 
for specific textbooks in the database and borrow the books (subject to availability).

### This Application Is ***Perfect*** For Currently Enrolled 👨‍🎓👩‍🎓 Who Wish To:
- Expand the textbook collection by donating their used textbooks
- Borrow the textbook from the library

### Source of ***Interest*** 💡

This idea is inspired by the university library where textbooks and various course books are available for loan. With 
textbooks being an essential yet expensive course material, many of the students like me feel a pinch in our hearts when 
buy an extra textbook. Therefore, this application serves as viable solution to lighten the financial burden for 
students who have a limited budget but still want to excel in their academics. Furthermore, after the courses ended last
the term, there were many students who wanted to clear up their study space but also found it a hassle to resell their 
course textbooks. Hence, this application solves that problem by taking those textbooks and including them into the 
current textbook list.


### User Stories 👤
- As a user, I want to be able to add a textbook to a list of textbooks if it is not already in the list
- As a user, I want to be prevented from adding a textbook to a list of textbooks if it is already in the list
- As a user, I want to be able to find a list of relevant textbooks by its title
- As a user, I want to be able to find a list of relevant textbook by its author
- As a user, I want to be able to narrow down the textbook list with the textbook edition
- As a user, I want to be able to borrow the textbook with the specific title, author and edition
- As a user, I want to be prevented from borrowing a book when it is at loan
- As a user, I want to be able to view the list of textbooks and their availability status
- As a user, I want to be able to save the library's list of textbooks and their availability status to file
- As a user, I want to be able to reload the library's list of textbooks from file when the program starts and resume 
 exactly where I left off at a later time

## Instructions for Grader:
- You can generate the first required event (adding a Textbook to the current Textbook list) by:
    1. Clicking on the "Donate" button
    2. Fill in the necessary information (Title, Author, Edition)
    3. Click on the "Confirm Donation" button
    4. If book does not already exist in library, it will be added to the current Textbook list. 
       Otherwise, book will not be added to the current Textbook list.
    5. You can view the Textbooks that have been added to the current Textbook List by clicking on the "View" button
- You can generate the second required event (borrowing a Textbook from the current Textbook list) by:
    1. Clicking on the "Borrow" button
    2. Click either the "Title" or "Author" button
    3. Fill in the title / author and press "ENTER" on keyboard to submit
    4. If title / author exists in current library, fill in the edition number on the new page.
       Otherwise, a "Beep" sound will play and a message saying "Sorry, there is no such book in the library!" will 
       show up
    5. If corresponding edition exists in current library and more than 1 textbook is found, the narrowed list of 
    textbooks will appear and user is brought back to the Main Menu. If a textbook is found, user will be prompted to 
    confirm loan. If textbook is found but is on loan, a "Beep" sound will play and a message indicating that book is on
    loan will show up. Otherwise, a "Beep" sound will play and a message saying "Sorry, there is no such book in the 
    library!" will show up
    6. Click on the "Yes" button in the Confirm Loan prompt to successfully borrow a book
- You can locate my visual components (images) at:
    1. Main Menu (Library Icon)
    2. Donation Page (Donate Book Icon) after clicking the "Donate" button
    3. Book Loan Page (Title and Author Icons) after clicking the "Borrow" button
- You can trigger the audio component ("Beep") by:
    1. Entering an invalid book title / author when you borrow the book
    2. Entering an invalid edition integer after filling in book title / author
    3. Attempting to borrow a book that is currently on loan
- You can save the state of my application by clicking on the "Save" button on the Main Menu
- You can auto-load the state of my application during the startup of the application

### Phase 4: Task 2
Java language construct: Test and design a class that is robust
- Exception: EmptyStringException
- Booklist class [Methods: listTitle() and listAuthor()] have a robust design

Java language construct: Include a type hierarchy in your code
- Abstract Class: GeneralBorrowTemplate
- BorrowTitleFrame, BorrowAuthorFrame and EditionFrame extends GeneralBorrowTemplate
- All 3 subclasses have distinct functionality: BorrowTitleFrame filters the Booklist based on title, BorrowAuthorFrame 
filters the Booklist based on title, EditionFrame filters the Booklist based on edition
- Implementation of keyPressed() in EditionFrame is different from that in BorrowTitleFrame and BorrowAuthorFrame

### Phase 4: Task 3
Problems:
    1. There is poor cohesion in the MainFrame class as it not only displays the main menu, but also initializes, saves 
    and loads the library list. The single responsibility of MainFrame should be the main menu display
    2. There is too much coupling in between every class in the ui package and Booklist class. This is due to the same
    booklist being passed around every time a change is made to the booklist.
    3. There is poor cohesion in the Booklist class as it is responsible for the maintenance of the Actual list,
    the Temporary list (filtered according to user's selections) and Temporary Textbook (final chosen textbook). The
    single responsibility of Booklist should be the maintenance of the Actual list. 
    
Improvements:
- Targeting problem 1: A new class, LibraryData which extends Booklist is created. While inheriting the methods and 
fields of Booklist, the init(), saveLibrary() and loadLibrary() methods are also moved from MainFrame class to 
LibraryData class. 
- Targeting problem 2: Singleton pattern is applied on LibraryData as it fulfills the 2 criteria: having only a single 
instance of the class and wanting a global point of access for that single instance because it will be used by many 
different classes in this code. Coupling between the class in the ui package and Booklist class has reduced 
significantly since the same booklist does not have to be passed back and forth to the MainFrame class. A significant 
removal of coupling exists in BorrowFrame as it used to have a Booklist field but it does not require the field now.
- Targeting problem 3: A new class, BooklistManager is created. The Temporary list and Temporary Textbook fields from 
Booklist class are transferred to BooklistManager to follow the Single Responsibility Principle. The associated method
functionalities are also transferred to BooklistManager.

### Image Citations
- Borrow Author Icon: https://www.pinclipart.com/picdir/big/252-2522417_author-icons-clipart.png
- Borrow Title Icon: https://www.iconfinder.com/icons/4099699/creative_creative_writing_editor_writer_writing_icon
- Donate Book Icon: https://www.vecteezy.com/vector-art/97081-donate-flat-icons-free-vector
- Library Icon: https://icons8.com/icons/set/library# textbook-library-demo
