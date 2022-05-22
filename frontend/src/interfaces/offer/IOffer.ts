import IProductRaw from '../product/IProduct';
import ICity from '../city/ICity';
import IOfferUser from './user/IOfferUser';

interface IOffer {
    id: string
    date: string,
    userOutput: IOfferUser,
    product: IProductRaw,
    city: ICity,
    price: number,
    availability : string
}
export default IOffer;
