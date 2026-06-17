# Beacon

Beacon is a Spring Boot MVC web app scaffold that uses:

- **Thymeleaf** for server-side rendering
- **HTMX** for partial page updates
- **DaisyUI** for polished UI components
- **AlpineJS** for lightweight client-side behavior

The starter includes a responsive top navigation, a hero section, an HTMX-loaded status card, and a styled footer.

## Requirements

- Java 25
- Apache Maven 3.9.16+ or the included Maven Wrapper

## Run locally

### Unix/Linux/macOS

```bash
./mvnw spring-boot:run
```

### Windows

```bash
./mvnw.cmd spring-boot:run
```

Open the app at [http://localhost:8080](http://localhost:8080).

## Build and test

```bash
./mvnw clean test
```

## Project structure

- `src/main/java` - Spring Boot application and MVC controller
- `src/main/resources/templates` - Thymeleaf views and fragments
- `src/main/resources/static` - Small custom CSS overrides
- `src/test/java` - MVC tests for the home page and HTMX fragment

## Issues and Contributions

Technical and non-technical issues can be reported to the [Issue Tracker](https://github.com/ikmdev/repo-seed/issues).

Contributions can be submitted via pull requests. Please check the [contribution guide](doc/how-to-contribute.md) for more details.

