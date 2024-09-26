import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/comics';  // Backend API base URL

// Fetch latest comics
export const getLatestComics = (page = 1) => {
    return axios.get(`${API_BASE_URL}/latest/${page}`);
};

// Fetch Marvel comics
export const getMarvelComics = (page = 1) => {
    return axios.get(`${API_BASE_URL}/marvel/${page}`);
};

// Fetch DC comics
export const getDCComics = (page = 1) => {
    return axios.get(`${API_BASE_URL}/dc/${page}`);
};

// Search comics by query
export const searchComics = (query, page = 1) => {
    return axios.get(`${API_BASE_URL}/search`, {
        params: {
            query,
            page
        }
    });
};
