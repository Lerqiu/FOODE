import { TextField } from "@mui/material";

const mainStyles = {
    backgroundColor: "#83BD75"
}

const nestedStyles = {
    color: "white"
}


const ProductOfferFiltrText = (props: { title: string }) => {
    return (
        <TextField
            label={props.title}
            defaultValue=""
            sx={{ m: 2 }}
            variant="filled"
            color="success"
            InputProps={{ style: nestedStyles }}
            InputLabelProps={{ style: nestedStyles }}
            style={mainStyles}
        />
    );
}

export default ProductOfferFiltrText;