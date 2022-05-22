import React from 'react';
import { Typography } from '@mui/material';
import OfferProduct_style from './OfferProduct.style';
import IProduct from '../../../../../interfaces/product/IProduct';

function OfferProduct(props: { product: IProduct }) {
  const { product } = props;

  return (
    <Typography
      variant="h5"
      noWrap
      style={OfferProduct_style}
      align="center"
    >
      {product.name}
    </Typography>
  );
}

export default OfferProduct;
