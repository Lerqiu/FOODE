import { Typography } from '@mui/material';
import { ProductOfferEntryTitle_style } from './ProductOfferEntryTitle.style';

const ProductOfferEntryTitle = (props: { title: string }) => {
    return (
        <Typography
            variant="h5"
            noWrap
            style={ProductOfferEntryTitle_style}
            align="center">
            {props.title}
        </Typography>
    )
}

export default ProductOfferEntryTitle;