import axios from 'axios';
import { getOffersPageManagement } from '../helpers/offerPageStorageHelper';
import { IPage } from '../interfaces/pagination/IPagination';
import IOffersResponse from '../interfaces/offersResponse/offersResponse';
import IOfferView from '../interfaces/offer/IOfferView';

const apiClient = axios.create({
  baseURL: process.env.REACT_APP_URL_PREFIX,
  headers: {
    'Content-type': 'application/json',
  },
});

export const Offer_handlePagination = (page: IPage): string => {
  let pageString = '';
  pageString += `&page=${page.currentPage - 1}`;
  pageString += `&size=${page.pageSize}`;
  return pageString;
};

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

const findAll = async (page: IPage) => {
  const storageManagement = getOffersPageManagement();
  const sort = storageManagement.sort ? `sort=${storageManagement.sort}` : '';
  const filter = handleFilters();
  const pagination = Offer_handlePagination(page);
  const response = await apiClient.get<IOffersResponse>(`/offers?${sort}${filter}${pagination}`);
  return response.data;
};

const save = async (offer: any) => {
  await apiClient.post('offers', offer);
};

const deleteOne = async (offer: IOfferView) => {
  await apiClient.delete(`offers/${offer.id}`);
};

const OfferService = {
  findAll,
  save,
  deleteOne,
};
export default OfferService;
