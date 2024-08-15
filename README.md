## TheOffice (WIP)

(The app is still in progress but the main logic is there we just need to implement the same logic that we have for user and desk for the rest of the classes.)

This app showcases the use of jetpack compose, kotlin, SSOT pattern and room to show how we can manage an office and his elements

### Tech stack
- Jetpack compose 
- Hilt
- Room

### Explanation

The app uses MVI pattern to comunicate between the screen and the viewmodel and the information is following UDF flows with screen state and actions, also we use multi module to separate layers since this apps is small we used layer modules but can be separated by features also, 
the app module is an android module and contains the ui and the viewmodels, domain is a java/kotlin module and has no reference to android since it's not required and holds the business logic, data is an android module because we are using room and holds the logic to store the data in SQL.

#### Schema explanation

The relation ships between clases is the following:

  - DeskEntity: Represents a desk with a unique ID (deskId) and a location.

  - KeyboardEntity: Represents a keyboard with a unique ID (keyboardId), a foreign key reference to a deskId, and a model. The deskId references a DeskEntity, establishing a one-to-many relationship between desks and keyboards. When a desk is deleted, its associated keyboards are also deleted (CASCADE).

  - ScreenEntity: Represents a screen with a unique ID (screenId), a foreign key reference to a deskId, and a model. Like KeyboardEntity, it has a one-to-many relationship with DeskEntity.

  - PersonEntity: Represents a person with a unique ID (personId) and a name.

  - PersonDeskCrossRef: This class is a cross-reference table for modeling a many-to-many relationship between PersonEntity and DeskEntity. It uses composite primary keys (personId, deskId) and establishes foreign key constraints to maintain referential integrity.
  
<img src="https://github.com/user-attachments/assets/c9a73079-f1c0-43fb-b29c-be761c2ff05c" width="200" height="450"/>
