import axios from 'axios';

const url_prefix = process.env.REACT_APP_URL_PREFIX;
const client = axios.create({
  baseURL: url_prefix,
});

const getOffersConnection = () => client;
export default getOffersConnection;
