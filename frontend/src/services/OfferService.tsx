import axios from 'axios';
import IOffers from '../interfaces/offer/IOffers';
import { getOffersPageManagement } from '../helpers/offerPageStorageHelper';

const apiClient = axios.create({
  baseURL: process.env.REACT_APP_URL_PREFIX,
  headers: {
    'Content-type': 'application/json',
  },
});

const handleFilters = () => {
  const storageManagement = getOffersPageManagement();
  const cityFromStorage = storageManagement.city;
  const city = cityFromStorage !== 'default' ? JSON.parse(storageManagement.city || '{}') : JSON.parse('{}');
  let filterString = '';
  filterString += storageManagement.priceFrom ? `&priceFrom=${storageManagement.priceFrom}` : '';
  filterString += storageManagement.priceTo ? `&priceTo=${storageManagement.priceTo}` : '';
  filterString += storageManagement.name ? `&name=${storageManagement.name}` : '';
  filterString += city?.id ? `&cityId=${city.id}` : '';
  return filterString;
};

const findAll = async () => {
  const storageManagement = getOffersPageManagement();
  const sort = storageManagement.sort ? `sort=${storageManagement.sort}` : '';
  const filter = handleFilters();
  const response = await apiClient.get<IOffers>(`/offers?${sort}${filter}`);
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
