import IOffers from '../offer/IOffers';
import { IPaginationRaw } from '../pagination/IPagination';

type IOffersResponse = IOffers & IPaginationRaw;
export default IOffersResponse;
