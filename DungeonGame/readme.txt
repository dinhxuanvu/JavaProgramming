1. I have designed the GUI using MVC pattern. I have combined the View and Controller as one file and Model as separate file. Also, I no longer used Abstract classes. As a result, I no longer had subclasses for Item, Trap, Hallway and Room. Now, I only have classes Trap, Hallway, Room and User.

I also changed some of the internal methods inside the classes as I added more methods inside the classes to make sure I can access to all of internal variables.

2. The first test case (test1.maz) is simply a general test case which has all of components. The purpose of the this test case is to test the reading configuration file function to ensure itÕs working properly. The test is also to test if the buttons and lists are working correctly. In addition, this test case has all of kind of trap which is useful to test the functionality of each trap. As the life force and level of the use are changing through each room, the test case should be able to test the updating functionality of the GUI as well.

The second test case (test2.maz) is used to test some useless items in the room. Also, all of the traps except for the trap in the first room are warp. The reason for that is to test if the program is able to handle warp the user continuously without crashing.
