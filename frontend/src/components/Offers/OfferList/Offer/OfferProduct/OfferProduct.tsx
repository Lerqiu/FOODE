import React from 'react';
import IProduct from '../../../../../interfaces/product/IProduct';
import OffersList_Typography from './OfferProduct.style';

function OfferProduct(props: { product: IProduct }) {
  const { product } = props;

  return (
    <OffersList_Typography
      variant="h5"
      noWrap
      align="center"
    >
      {product.name.toUpperCase()}
    </OffersList_Typography>
  );
}

export default OfferProduct;
