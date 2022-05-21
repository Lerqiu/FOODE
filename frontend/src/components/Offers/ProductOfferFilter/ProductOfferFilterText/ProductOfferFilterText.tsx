import React from 'react';
import { TextField } from '@mui/material';
import { ProductOfferFilterText_style, ProductOfferFilterText_style_nested } from './ProductOfferFilterText.styles';

function ProductOfferFilterText(props: { title: string }) {
  const { title } = props;

  return (
    <TextField
      label={title}
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
