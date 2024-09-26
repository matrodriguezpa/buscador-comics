import React from 'react';
import ComicSearch from './ComicSearch';
import Header from './Header';
import Footer from './Footer';
import './index.css'; // Assuming you have a main CSS file

function App() {
  return (
    <div className="app-container">
      <Header />
      <ComicSearch />
      <Footer />
    </div>
  );
}

export default App;