import axios from 'axios';
import IOffers from '../interfaces/offer/IOffers';

const apiClient = axios.create({
  baseURL: process.env.REACT_APP_URL_PREFIX,
  headers: {
    'Content-type': 'application/json',
  },
});

const findAll = async () => {
  const response = await apiClient.get<IOffers>('/offers?cityId=1');
  return response.data;
};

const save = async (offer : any) => {
  await apiClient.post('offers', offer);
};

const OfferService = {
  findAll,
  save,
};
export default OfferService;
