import React, { useEffect, useState } from 'react';
import { getLatestComics } from '../services/comicService';  // Import service functions

const ComicList = () => {
    const [comics, setComics] = useState([]);  // State to store the comics
    const [page, setPage] = useState(1);  // State for pagination

    useEffect(() => {
        fetchComics(page);  // Fetch comics whenever the page changes
    }, [page]);

    const fetchComics = async (page) => {
        try {
            const response = await getLatestComics(page);  // Call the API
            setComics(response.data);  // Set the comics from the API response
        } catch (error) {
            console.error("Error fetching comics", error);  // Handle errors
        }
    };

    return (
        <div>
            <h1>Latest Comics</h1>
            <div className="comics-grid">
                {comics.map((comic, index) => (
                    <div key={index} className="comic-item">
                        <img src={comic.coverPage} alt={comic.title} />
                        <h3>{comic.title}</h3>
                        <p>{comic.description}</p>
                    </div>
                ))}
            </div>
            <button onClick={() => setPage(page + 1)}>Next Page</button>
        </div>
    );
};

export default ComicList;
