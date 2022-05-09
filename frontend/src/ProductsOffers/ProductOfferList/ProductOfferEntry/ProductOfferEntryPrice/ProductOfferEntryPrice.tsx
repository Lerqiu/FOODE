import { Button } from '@mui/material';
import { ProductOffersPrice_style } from './ProductOffersPrice.style';


export interface IProductOffersPrice {
    price: string
}

const ProductOffersPrice = (props: { data: IProductOffersPrice }) => {
    return (
        <Button
            variant="contained"
            sx={{ m: 2 }}
            size="small"
            color="success"
            style={ProductOffersPrice_style}>
            Cena {props.data.price} pkt
        </Button>
    )
}

export default ProductOffersPrice;