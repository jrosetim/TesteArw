import axio from 'axios';

const api = axio.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Access-Control-Allow-Origin': '*'
  } 
});

export default api;