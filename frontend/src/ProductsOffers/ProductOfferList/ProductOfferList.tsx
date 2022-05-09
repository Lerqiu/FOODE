import { List } from "@mui/material"
import IProductOffersEntry from "./ProductOfferEntry/IProductOfferEntry";
import ProductOfferEntry from "./ProductOfferEntry/ProductOfferEntry";

const ProductOfferList = (props: { list: IProductOffersEntry[] }) => {
    return (
        <List>
            {
                props.list.map(data => <ProductOfferEntry data={data} key={data.id} />)
            }
        </List>
    )
}

export default ProductOfferList;