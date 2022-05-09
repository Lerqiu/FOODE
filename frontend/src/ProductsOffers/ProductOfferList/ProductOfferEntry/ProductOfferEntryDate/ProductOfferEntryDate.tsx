import { Button } from '@mui/material';
import { ProductOfferEntryDate_style } from './ProductOfferEntryDate.style';

export interface IProductOffersEntryDate {
    day: string,
    month: string,
    year: string
}

const ProductOfferEntryDate = (props: { date: IProductOffersEntryDate, prefix: string }) => {
    const date = props.date
    const text = `${props.prefix} ${date.day}-${date.month}-${date.year}`

    return (
        <Button
            variant="contained"
            disabled
            size="small"
            style={ProductOfferEntryDate_style}>
            {text}
        </Button>
    )
}

export default ProductOfferEntryDate;