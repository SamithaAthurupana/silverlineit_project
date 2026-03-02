import React, { useState } from "react";
import axios from "axios";
import "../App.css";

function Login() {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = async () => {

    try {
      const res = await axios.post("http://localhost:8080/api/auth/login", {
        username,
        password
      });

      localStorage.setItem("token", res.data.token);
      window.location.href = "/";
    } catch (err) {
      setError("Invalid username or password");
    }
  };

  return (
    <div className="login-container">

      <div className="login-card">
        <h2>Login</h2>

        <input
          type="text"
          placeholder="Username"
          onChange={(e) => setUsername(e.target.value)}
        />

        <input
          type="password"
          placeholder="Password"
          onChange={(e) => setPassword(e.target.value)}
        />

        {error && <p className="error">{error}</p>}

        <button onClick={handleLogin}>Login</button>
      </div>

    </div>
  );
}

export default Login;