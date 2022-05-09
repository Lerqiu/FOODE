import { IProductOffersEntryDate } from "./ProductOfferEntryDate/ProductOfferEntryDate"
import { IProductOffersLocation } from "./ProductOfferEntryLocation/ProductOfferEntryLocation"
import { IProductOffersPrice } from "./ProductOfferEntryPrice/ProductOfferEntryPrice"
import { IProductOffersUser } from "./ProductOfferEntryUser/ProductOfferEntryUser"

interface IProductOffersEntry {
    id: string
    addDate: IProductOffersEntryDate,
    expirationDate: IProductOffersEntryDate,
    user: IProductOffersUser,
    imgSrc: string,
    productName: string,
    location: IProductOffersLocation,
    price: IProductOffersPrice
}
export default IProductOffersEntry;