import React, { useState } from 'react';
import { searchComics } from '../services/comicService';  // Import the search function

const ComicSearch = () => {
    const [query, setQuery] = useState('');  // State to store the search query
    const [comics, setComics] = useState([]);  // State to store search results

    const handleSearch = async () => {
        try {
            const response = await searchComics(query);  // Search for comics
            setComics(response.data);  // Set the comics from the search results
        } catch (error) {
            console.error("Error searching comics", error);  // Handle errors
        }
    };

    return (
        <div>
            <input 
                type="text"
                value={query}
                onChange={(e) => setQuery(e.target.value)}  // Update query state on input change
                placeholder="Search for a comic"
            />
            <button onClick={handleSearch}>Search</button>

            <div className="comics-grid">
                {comics.map((comic, index) => (
                    <div key={index} className="comic-item">
                        <img src={comic.coverPage} alt={comic.title} />
                        <h3>{comic.title}</h3>
                        <p>{comic.description}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ComicSearch;
