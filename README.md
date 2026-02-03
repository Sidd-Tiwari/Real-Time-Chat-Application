
# Real-Time Chat Application (Spring Boot + WebSocket)

A real-time chat application built using **Spring Boot**, **WebSocket**, and **STOMP** that enables instant message delivery between connected users. This project demonstrates real-time communication using a simple in-memory message broker and **SockJS** for fallback support.

---

## 🚀 Features

- Real-time messaging using WebSocket  
- STOMP protocol support  
- SockJS fallback for browser compatibility  
- Simple and clean chat UI  
- Broadcast messages to all connected users  
- Lightweight and fast startup  

---

## 🛠 Tech Stack

- Java 21+  
- Spring Boot  
- Spring WebSocket  
- STOMP  
- SockJS  
- Maven Wrapper  
- HTML + JavaScript  

---

## 📂 Project Structure

```

src/main/java
└── com.chat.app
├── config        # WebSocket configuration
├── controller    # Chat controller
├── model         # Chat message model
└── AppApplication.java

src/main/resources
└── templates
└── chat.html

```

---

## ▶️ How to Run the Project

1️⃣ Clone the repository  

```
git clone https://github.com/Sidd-Tiwari/real-time-chat-app.git
cd real-time-chat-app
````

2️⃣ Run using Maven Wrapper (no Maven install needed)

```bash
.\mvnw spring-boot:run
```

3️⃣ Open in Browser

```
http://localhost:8080/chat
```

## Testing Purpose
```
https://real-time-chat-application-z2r3.onrender.com
```

---

## 🔄 WebSocket Flow

* Client connects to `/ws`
* Messages sent to `/app/sendMessage`
* Messages broadcasted to `/topic/messages`
* All subscribed clients receive updates instantly

---

## 🧪 Sample Message Payload

```json
{
  "sender": "User",
  "content": "Hello World!"
}
```

---

## 📌 Future Enhancements

* User authentication
* Private chat rooms
* Message persistence (MySQL/MongoDB)
* Typing indicators
* Message timestamps

---

## 📜 License

This project is licensed under the **MIT License**.

---

## 🙌 Author

**Siddharth Tiwari**

Full Stack Developer | Java | Spring Boot

[GitHub](https://github.com/Sidd-Tiwari) | [LinkedIn](https://www.linkedin.com/in/siddharth-tiwari-394451271/)

