# service--booking-rental-platform
Booking service for a property rental platform

## Documents

All diagrams used to build this project were made using draw.io. To edit or view them in detail, please open the URL below and import the `architecture.drawio` file from the project's `doc` folder.

[draw.io](https://app.diagrams.net/#)

### Use Case diagram
To understand the business needs, two use case diagrams were created. The first one separates users by roles, while the second simplifies it by combining all features for a single user.

![Architecture-UseCase.png](docs%2FArchitecture-UseCase.png)

### Database diagram
The database modeling was done using the tool [dbdiagram](https://dbdiagram.io/d). The code is available in the `docs` folder, or you can access it on the draw.io page under the "Database" tab.

![Architecture-database.png](docs%2FArchitecture-database.png)

### Architecture
Netflix Hexagonal was chosen as the architecture model for this project due to its highly decoupled structure, which uses adapters and clearly separated layers.

**Netflix Hexagonal**

![Architecture-netflix-hexagonal.png](docs%2FArchitecture-netflix-hexagonal.png)

**API Diagram**

Below is a diagram that represents a simplified view of the API endpoints and flows.

![Architecture-Api - Planning.png](docs%2FArchitecture-Api%20-%20Planning.png)

**Architecture Brainstorm**

To envision how to scale this service in the future, this architecture diagram was conceptualized with deployment in an AWS with k8s environment. Additionally, there are two other diagrams showing a brainstorm of the approach I would take to scale this API.

![Architecture-Architecture.png](docs%2FArchitecture-Architecture.png)
## Collections
Download the Postman collections for testing all the API endpoints from the link below or find them in the `docs` folder.

[rental-platform.postman_collection.json](docs%2Frental-platform.postman_collection.json)

## Technical aspects

### Tools

- Java 21
- [GraalVM 21](https://www.graalvm.org/downloads/#)
- Spring 3
- H2 Database
- FlyWay Migrations
- Lombok
- MapStruct
- JUnit 5, Mockito, MockMvc
- JPA with Hibernate
- Postman for testing
- GitHub
- GitFlow with conventional commits

### Unit Tests

Unit tests have been implemented to cover the main points and methods of the service. The project overall has achieved 93% test coverage.

![coverage.png](docs%2Fcoverage.png)

### Work and versioning

To ensure organization, four main tasks were decided upon to develop this project:

- **TSK01** - Project structure
- **TSK02** - Booking and Block operations
- **TSK03** - Unit Tests and adjustments
- **TSK04** - Update and upload documentation

Based on these tasks, versioning was done by separating different feature branches. They are:

- `main`
- `develop`
- `feature`
    - `TSK01-project-structure`
    - `TSK02-booking-block-operations`
    - `TSK03-unit-tests`
    - `TSK04-documentation`
