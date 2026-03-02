import React, { useState, useEffect } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";
import "../App.css";

function Dashboard() {

  const [file, setFile] = useState(null);
  const [files, setFiles] = useState([]);
  const [progress, setProgress] = useState(0);
  const [message, setMessage] = useState("");

  const token = localStorage.getItem("token");

  const fetchFiles = async () => {
    const res = await axios.get("http://localhost:8080/api/course", {
      headers: { Authorization: `Bearer ${token}` }
    });
    setFiles(res.data);
  };

  useEffect(() => {
    fetchFiles();
  }, []);

  const handleUpload = async () => {

    if (!file) {
      setMessage("Please select a file");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    await axios.post(
      "http://localhost:8080/api/course/upload",
      formData,
      {
        headers: { Authorization: `Bearer ${token}` },
        onUploadProgress: (e) => {
          const percent = Math.round((e.loaded * 100) / e.total);
          setProgress(percent);
        }
      }
    );

    setMessage("File uploaded successfully!");
    setProgress(0);
    fetchFiles();
  };

  return (
    <>
      <Navbar />

      <div className="container">

        <div className="card">

          <h2>Upload File</h2>

          <input type="file" onChange={(e) => setFile(e.target.files[0])} />

          {progress > 0 && (
            <div className="progress-container">
              <div
                className="progress-bar"
                style={{ width: `${progress}%` }}
              ></div>
            </div>
          )}

          {message && <p className="success">{message}</p>}

          <button onClick={handleUpload}>Upload</button>

        </div>

        <div className="card">

          <h2>Uploaded Files</h2>

          <table>
            <thead>
              <tr>
                <th>Name</th>
                <th>Type</th>
                <th>Size</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {files.map(f => (
                <tr key={f.id}>
                  <td>{f.fileName}</td>
                  <td>{f.fileType}</td>
                  <td>{(f.fileSize / 1024).toFixed(2)} KB</td>
                  <td>
                    <a
                      href={`http://localhost:8080/api/course/download/${f.id}`}
                      target="_blank"
                      rel="noreferrer"
                      className="download-link"
                    >
                      Download
                    </a>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

        </div>

      </div>
    </>
  );
}

export default Dashboard;