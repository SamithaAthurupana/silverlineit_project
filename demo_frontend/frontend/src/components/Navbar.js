import React from "react";
import "../App.css";

function Navbar() {

  const logout = () => {
    localStorage.removeItem("token");
    window.location.href = "/login";
  };

  return (
    <div className="navbar">
      <h3>Course Upload System</h3>
      <button onClick={logout}>Logout</button>
    </div>
  );
}

export default Navbar;