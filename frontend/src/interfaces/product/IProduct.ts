interface IProduct {
    id: number,
    name: string,
    expirationDate: Date
}
export default IProduct;

export interface IProductRaw {
    id: number,
    name: string,
    expirationDate: string
}
