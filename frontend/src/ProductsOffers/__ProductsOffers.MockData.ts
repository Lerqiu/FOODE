import IProductOffersEntry from "./ProductOfferList/ProductOfferEntry/IProductOfferEntry"

const ProductsOffers_MockData = (): IProductOffersEntry[] => {
    const single = {
        addDate: {
            day: "17",
            month: "05",
            year: "2022"
        },
        expirationDate: {
            day: "18",
            month: "05",
            year: "2022"
        },
        user: { login: "Olek" },
        imgSrc: "https://media.discordapp.net/attachments/966704303119171658/970037795995398144/unknown.png",
        productName: "Jabłko",
        location: { location: "Wrocław, Kota 24" },
        price: { price: "12" }
    }
    let array: IProductOffersEntry[] = []
    for (let i = 0; i < 15; i++)
        array.push({ ...single, id: i.toString() })
    return array
}

export default ProductsOffers_MockData;