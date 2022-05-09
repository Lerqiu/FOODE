import { TextField } from "@mui/material";
import { ProductOfferFilterText_style, ProductOfferFilterText_style_nested } from "./ProductOfferFilterText.styles";

const ProductOfferFilterText = (props: { title: string }) => {
    return (
        <TextField
            label={props.title}
            defaultValue=""
            sx={{ m: 2 }}
            variant="filled"
            color="success"
            InputProps={{ style: ProductOfferFilterText_style_nested }}
            InputLabelProps={{ style: ProductOfferFilterText_style_nested }}
            style={ProductOfferFilterText_style}
        />
    );
}

export default ProductOfferFilterText;