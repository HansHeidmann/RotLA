# RotLA
- Hans Heidmann
- Peter Loden

Java Version: 16
---------------------------------------------------------------------------
3.2

UML Updates: 
We changed a handful of things during the actual implementation. We ended up using the PropertyChangeSupport class for publishing events, which is from the recommended Observer pattern linked in the class slides (https://www.baeldung.com/java-observer-pattern). So, our variable/method names in Logger and Tracker also changed a bit to reflect the nature of implementation and we had to add a couple of methods to add/remove PropertyChangeListeners in our Adventurer/Creature super-classes. While working on the CombatAlgorithm strategy pattern, we were able to remove fight() and rollDice() methods in Adventurer subclasses, since we would be adding to the subclasses of CombatAlgorithm. CombatAlgorithm now has a method for celebrate() as well as rollDice(), which is overridden in the subclasses. We built our CelebrateDecorator class to be very similar to the Starbuzz example from the class slides - it uses a getCelebrations() method (instead of getDescription) that builds onto the end of the string via instantiation of new Decoration objects. Since the game is still simple, the subclasses of SearchAlgorithm were able to be completed using only their constructors, so we removed the override methods. The Treasure object system worked out exactly as envisioned while doing the UML for part 3.1 and object references live within the Adventurers inventories as previously specified. Only a couple methods needed to be added to our Room class to manage addition of treasure during facility generation and removal of treasure when Adventurers discover it. Inside of BoardRenderer, we added references for Logger and Tracker and removed a few functions we didn't end up populating after all.

Other Notes:

---------------------------------------------------------------------------
2.2 

UML Updates: 
We did not have to change the overall structure of the initial UML diagram other than the removal of the Facility class. 
However the attributes and methods of each class had to be changed quite a bit. The BoardRenderer class saw the largest changes with 5 attributes and 6 methods being added, as well as two methods being removed. Other classes have simliar changes though none are quite as extensive. 

Other Notes: 
As another student and the instructor discussed on Piazza, their is a fairly low chance of the game ending from any situation other than all of the 10 treasures being found. It would make sense to require higher dice rolls for adventurers to find treasure, or to require the team of adventurers to amass around 50 treasures, in order to even things up if none of the other game mechanics are to be altered. 
