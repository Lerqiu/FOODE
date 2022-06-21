import axios from 'axios';

const url_prefix = process.env.REACT_APP_URL_PREFIX;
const apiClient = axios.create({
  baseURL: url_prefix,
  headers: {
    'Content-type': 'application/json',
  },
});

export default apiClient;
