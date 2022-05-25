import axios from 'axios';
import ICity from '../interfaces/city/ICity';

const apiClient = axios.create({
  baseURL: process.env.REACT_APP_URL_PREFIX,
  headers: {
    'Content-type': 'application/json',
  },
});

const findAll = async () => {
  const response = await apiClient.get<ICity[]>('/city');
  return response.data;
};

const CityService = {
  findAll,
};
export default CityService;
