import IProduct from "../product/IProduct";
import ICity from "../city/ICity";
import IOfferUser from "./user/IOfferUser";

interface IOffer {
    id: string
    date: Date,
    user: IOfferUser,
    product: IProduct,
    city: ICity,
    price: number
}
export default IOffer;