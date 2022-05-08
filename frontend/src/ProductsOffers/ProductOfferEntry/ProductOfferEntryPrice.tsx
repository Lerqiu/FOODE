import { Button } from '@mui/material';


export interface IProductOffersPrice {
    price: string
}

const ProductOffersPrice = (props: { data: IProductOffersPrice }) => {
    return <Button
        variant="contained"
        disabled
        size="small"
        style={{ backgroundColor: "#4E944F", color: "white" }}
    >Cena {props.data.price} pkt</Button>;
}

export default ProductOffersPrice;