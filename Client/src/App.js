// src/App.js
import React from 'react';
import ComicSearch from './ComicSearch';
import Header from './Header';
import Footer from './Footer';

const App = () => {
  return (
    <div>
    <Header/>
    <ComicSearch />
    <Footer/>
    </div>
  );
};

export default App;