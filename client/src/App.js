import React, { useState } from "react";
import SockJsClient from "react-stomp";
import "./App.css";
import Input from "./components/input";
import Form from "./components/form";
import Chat from "./components/chat";
import api from "./services/api";

const SOCKET = "http://localhost:8080/chat/";

const App = () => {
  const [messages, setMessages] = useState([]);
  const [user, setUser] = useState(null);
  let onConnected = () => {
    console.log("Connected!!");
  };
  let onMessageReceived = (message) => {
    setMessages(messages.concat(message));
  };
  let onSendMessage = (text) => {
    api
      .post(user.username, text)
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.error(error);
      });
  };
  let handleLogin = (username) => {
    setUser({
      username: username,
      color: '#' + Math.floor(Math.random() * 0xFFFFFF).toString(16),
    });
  };
  return (
    <div className="App">
      {!!user ? (
        <>
          <SockJsClient
            url={SOCKET}
            topics={["/topic/group"]}
            onConnect={onConnected}
            onDisconnect={console.error("Disconnected!")}
            onMessage={(message) => onMessageReceived(message)}
            debug={false}
          />
          <Chat messages={messages} currentUser={user} />
          <Input onSendMessage={onSendMessage} />
        </>
      ) : (
        <Form onSubmit={handleLogin} />
      )}
    </div>
  );
};

export default App;
