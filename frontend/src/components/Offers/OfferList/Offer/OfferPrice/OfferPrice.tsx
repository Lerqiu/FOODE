import { Button } from '@mui/material';
import { OffersPrice_style } from './OffersPrice.style';


const OffersPrice = (props: { price: number }) => {
    const {price} = props;

    const getButtonMessage = () => {
        return `Cena ${price} pkt`;
    }

    return (
        <Button
            variant="contained"
            sx={{ m: 2 }}
            size="small"
            color="success"
            style={OffersPrice_style}>
            {getButtonMessage()}
        </Button>
    )
}

export default OffersPrice;