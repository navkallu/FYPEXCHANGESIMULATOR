# ExchangeFix Simulator

This is a Java-based Exchange Simulator designed to simulate trading activities and test order and market data handling systems. The project leverages technologies such as FIX protocol, PostgreSQL, and various modular services.

## 📁 Project Structure

```
WS_2025/
├── WS_2025/
│   ├── marketData.json
│   ├── pom.xml
│   ├── orderbook/
│   │   └── orderbook.json
│   ├── target/
│   │   ├── exchangefix-simulator-1.0-SNAPSHOT.jar
│   │   ├── test-classes/
│   │   │   └── com/exsim/...
│   │   ├── classes/
│   │   │   ├── exsim.cfg
│   │   │   ├── ordersender.cfg
│   │   │   ├── Suksim.txt
│   │   │   └── com/exsim/app/SimulatorMain.class
│   ├── src/
│   │   ├── main/java/com/exsim/
│   │   │   ├── app/
│   │   │   ├── db/
│   │   │   ├── service/
│   │   │   ├── domain/
│   │   │   ├── ordersender/
│   │   │   └── ...
│   │   └── test/java/com/exsim/
│   │       ├── app/
│   │       ├── db/
│   │       ├── service/
│   │       └── domain/
```

## ⚙️ Technologies Used

- **Java 17**
- **JUnit 5** for unit testing
- **Mockito** for mocking and static mocking
- **FIX Protocol (QuickFIX/J)**
- **PostgreSQL** for persistence
- **Maven** for build automation

## 🚀 Getting Started

1. **Install Java 17 and Maven**
2. **Configure PostgreSQL** and make sure it’s running on `localhost:5432` with the expected credentials.
3. **Run using IntelliJ** or via:
   ```bash
   mvn clean install
   mvn exec:java -Dexec.mainClass="com.exsim.app.SimulatorMain"
   ```

## 🧪 Running Tests

Run all unit tests using:

```bash
mvn test
```

> If using IntelliJ, you can run tests with or without coverage from the `Run` menu.

## 🔒 Note on Deprecated Methods

Some warnings are shown due to deprecated constructors like `new Double(String)`. Consider migrating to `Double.parseDouble(String)` for future-proofing.

## Notes
- Ensure PostgreSQL is running on `localhost:5432`.
- Database schema should be set up beforehand using SQL provided.
