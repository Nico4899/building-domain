# Building Domain Constraints
## Building

```
context Building inv:
self.regular.matches(^[0-9]{2}[.][0-9]{2}?$)
self.east.matches(^[0-9]{2}[.][0-9]{2}[A-Z]?$)
self.northOne.matches(^[0-9]{3,4}?$)
self.northTwo.matches(^[0-9]{4}[.][0-9]{1,2}?$)
self.northThree.matches(^[0-9]{1}[.][0-9]{3}[.][0-9]{1,2}?$)
self.northFour.matches(^[0-9]{3}[\/][0-9]{1}?$)
self.northFive.matches(^[0-9]{3}[a-z]{1}?$)
self.name -> notEmpty()
self.aoNumber -> notEmpty() implies self.aoNumber.toString().matches(regular) |
                self.aoNumber.toString().matches(east) | self.aoNumber.toString().matches(northOne) |
		self.aoNumber.toString().matches(northTwo) | self.aoNumber.toString().matches(northThree) |
                self.aoNumber.toString().matches(northFour) | self.aoNumber.toString().matches(northFive)
self.numFloors -> notEmpty()
self.lat -> notEmpty()
self.lon -> notEmpty()
self.campusLocation -> notEmpty()

context Building::getComponents(): Set<Component>
body: self.components

context Building::getProblems(): Set<Problem>
body: self.problems

context Building::addComponent(component:Component):
pre: not self.getComponents()->exists(component)
post: self.getComponents()->exists(component)

context Building::removeComponent(component:Component):
pre: self.getComponents()->exists(component)
post: not self.getComponents()->exists(component)

context Building::getRooms(): Set<Room>
body: self.rooms

context Building::addRoom(room:Room):
pre: not self.getRooms()->exists(room)
post: self.getRooms()->exists(room)

context Building::removeRoom(room:Room):
pre: self.getRooms()->exists(room)
post: not self.getRooms()->exists(room)

context Building::addProblem(problem:Problem):
pre: not self.getProblems()->exists(problem)
post: self.getProblems()->exists(problem)

context Building::removeProblem(problem:Problem):
pre: self.getProblems()->exists(problem)
post: not self.getProblems()->exists(problem)
```

## Room
```
context Room inv:
self.name->notEmpty()
self.aoNumber->notEmpty()

context Room::getComponents(): Set<Component>
body: self.components

context Room::getProblems(): Set<Problem>
body: self.problems

context Room::addComponent(component:Component):
pre: not self.getComponents()->exists(component)
post: self.getComponents()->exists(component)

context Room::removeComponent(component:Component):
pre: self.getComponents()->exists(component)
post: not self.getComponents()->exists(component)

context Room::addProblem(problem:Problem):
pre: not self.getProblems()->exists(problem)
post: self.getProblems()->exists(problem)

context Room::removeProblem(problem:Problem):
pre: self.getProblems()->exists(problem)
post: not self.getProblems()->exists(problem)
```

## Component
```
context Component inv:
self.type->notEmpty()

context Component::getComponents(): Set<Component>
body: self.components

context Component::getProblems(): Set<Problem>
body: self.problems

context Component::addComponent(component:Component):
pre: not self.getComponents()->exists(component)
post: self.getComponents()->exists(component)

context Component::removeComponent(component:Component):
pre: self.getComponents()->exists(component)
post: not self.getComponents()->exists(component)

context Component::addProblem(problem:Problem):
pre: not self.getProblems()->exists(problem)
post: self.getProblems()->exists(problem)

context Component::removeProblem(problem:Problem):
pre: self.getProblems()->exists(problem)
post: not self.getProblems()->exists(problem)

```

## Notification
```
context Notification inv:
self.title->notEmpty()
self.description->notEmpty()
self.date->notEmpty()
```
