import IProduct from "../product/IProduct";
import ICity from "../city/ICity";
import IOfferUser from "./user/IOfferUser";

interface IOfferView {
    id: string
    date: Date,
    expirationDate: Date,
    userOutput: IOfferUser,
    product: IProduct,
    city: ICity,
    price: number,
    imgSrc: string
}
export default IOfferView;