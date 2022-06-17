# Building Domain Constraints
## Building

[Building Constraints](/constraints/Building.txt)

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
