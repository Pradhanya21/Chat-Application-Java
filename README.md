# 🗨️ Chat Application with User Authentication

Welcome to the **Chat Application**! This Java-based chat application allows real-time communication between users with robust login and signup features.

## 📚 Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [Running the Application](#running-the-application)
- [Code Structure](#code-structure)
- [User Database](#user-database)
- [Commands](#commands)
- [Contributing](#contributing)
- [License](#license)

## 🚀 Features

- **Real-time Chat:** Instantly send and receive messages.
- **User Authentication:** Secure login and signup processes.
- **Broadcasting:** Messages are sent to all connected clients.
- **Multithreading:** Efficiently handles each client in a separate thread.

## 🛠️ Getting Started

To set up the Chat Application locally, follow these instructions:

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/pradhanyars/Chat-Application-Java.git
    cd Chat-Application-Java
    ```

2. **Compile the Code:**

    Make sure you have JDK installed. Compile the Java files using:

    ```bash
    javac *.java
    ```

3. **Run the Server:**

    Start the server that listens on port `12345`:

    ```bash
    java Server
    ```

4. **Run the Client:**

    Open another terminal and start the client application:

    ```bash
    java Client
    ```

## 🏃 Running the Application

### Starting the Server

To launch the server, execute:

```bash
java Server
```

**What to Expect:**

- The server listens on port `12345`.
- Waits for client connections and manages them in separate threads.
- Handles commands, manages user sessions, and broadcasts messages.

### Starting the Client

In a new terminal, start the client with:

```bash
java Client
```

**What to Expect:**

- Connects to the server at `localhost` on port `12345`.
- Allows input for commands related to signup, login, and chatting.
- Displays responses from the server in real-time.

## 🛠️ Commands

Interact with the application using the following commands:

- **SIGNUP [username] [password]:** Create a new user account.
- **LOGIN [username] [password]:** Log in to an existing account.
- **CHAT [message]:** Send a chat message to all connected clients.

### Examples:

- **SIGNUP Pradhanya 12345**

  Creates a new account with the username `Pradhanya` and password `12345`.

- **LOGIN Pradhanya 12345**

  Logs in with the username `Pradhanya` and password `12345`.

- **CHAT Hello, everyone!**

  Sends the message "Hello, everyone!" to all connected clients.

## 🗂️ Code Structure

- **`Server.java`**: Manages client connections, sessions, and message broadcasting.
- **`Client.java`**: Provides the user interface, sends commands, and displays messages.
- **`User.java`**: Handles user attributes and authentication methods.

## 📁 User Database

User credentials are stored in `users.txt` in this format:

```text
username1 password1
username2 password2
```

Place this file in the same directory as the server application.

## 🤝 Contributing

Contributions are welcome! Feel free to fork the repository and submit pull requests.

