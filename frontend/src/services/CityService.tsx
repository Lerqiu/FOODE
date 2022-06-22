import apiClient from '../components/Offers/Axios/Connection';
import ICity from '../interfaces/city/ICity';

const findAll = async () => {
  const response = await apiClient.get<ICity[]>('/city');
  return response.data;
};

const CityService = {
  findAll,
};
export default CityService;
