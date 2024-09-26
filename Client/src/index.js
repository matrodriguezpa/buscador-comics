import React from "react";
import ReactDOM from "react-dom/client";
import ComicSearch from "./components/ComicSearch";  // Import the ComicSearch component
import './index.css';  // Import your global CSS file

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<ComicSearch />);  // Render the ComicSearch component
