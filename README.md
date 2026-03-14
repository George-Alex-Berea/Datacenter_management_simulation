# Application Logic

* **Main Entry Point**: The `main` function handles case validation (checking for 2 or 4 parameters). The logic for line analysis and parsing is encapsulated within the methods of classes that inherit from `FileHandler`.
* **File Handling**: The abstract class `FileHandler` implements the core line-reading logic, delegating specific command-parsing implementations to its subclasses.
* **Performance Optimization**: I took the liberty of replacing `Sets` within the `Database` with `Maps`. This allows for element access in $O(1)$ time complexity, rather than the $O(\log n)$ complexity associated with a `TreeSet`.

---

# Design Patterns

## Singleton
The **Database** is implemented as a **Singleton**. This ensures the database instance is easily accessible from anywhere in the application without the need to pass it as a parameter. It also increases application robustness by preventing the accidental creation of multiple instances.

## Builder
The **Builder Pattern** is utilized for three specific classes:
* **Server** and **Location**: Chosen due to the high number of optional parameters required for these objects.
* **ResourceGroup**: Specifically used when a `Server` is required as a parameter (during the 3-file execution case) to facilitate the Observer alert propagation.

> **Note:** The Builder pattern was chosen over standard setters to ensure objects remain **immutable** after creation, eliminating the risk of accidental state changes.

## Factory
The application utilizes three **Factories**:
* **FileHandlerFactory**: Instantiates the appropriate output files based on the context. This abstracts the creation logic, allowing the application to select the correct handler based solely on the first command-line argument.
* **ServerUserFactory**: Generates users with specific roles (`User`, `Operator`, `Admin`) when parsing data from a "servers" file.
* **GroupUserFactory**: Functions similarly to the `ServerUserFactory` but for "groups" files. It exists separately because the keys for identical parameters differ, and these users must also be registered within the `ResourceGroup` list.

## Observer
A **"Cascading" Observer** logic is implemented:
1.  **ResourceGroup** acts as an observer for the **Server** sharing its IP.
2.  **Users** within a `ResourceGroup` act as observers for that **ResourceGroup**.

**Workflow:**
When an alert is triggered, the `Server` notifies the `ResourceGroup`, which then automatically propagates the notification to all registered members.
