import React, { useState } from "react";
import axios from "axios";
import '../index.css';

function ComicSearch() {
  const [date, setDate] = useState("");
  const [publisher, setPublisher] = useState("");
  const [comics, setComics] = useState([]);

  const searchComics = () => {
    const params = new URLSearchParams();
    if (date) params.append("date", date);
    if (publisher) params.append("publisher", publisher);

    axios
      .get(`http://localhost:8080/api/comics/new-releases?${params.toString()}`)
      .then((response) => {
        console.log("API Response: ", response.data);
        setComics(response.data || []);
      })
      .catch((error) => {
        console.error("There was an error fetching the comics!", error);
      });
  };

  return (
    <div className="main">
      <div>
        <label>
          Release Date:
          <input
            type="date"
            value={date}
            onChange={(e) => setDate(e.target.value)}
          />
        </label>
        <label>
          Publisher:
          <input
            type="text"
            value={publisher}
            onChange={(e) => setPublisher(e.target.value)}
          />
        </label>
        <button onClick={searchComics}>Search</button>
      </div>

      <div>
        {comics.length > 0 ? (
          <ul>
            {comics.map((comic) => (
              <li key={comic.id}>
                <h2>{comic.title}</h2>
                <p>Publisher: {comic.publisher?.name || "Unknown"}</p>
                <p>Release Date: {comic.releaseDate}</p>
                {/* Add more fields as necessary */}
              </li>
            ))}
          </ul>
        ) : (
          <p>No comics found.</p>
        )}
      </div>
    </div>
  );
}

export default ComicSearch;
