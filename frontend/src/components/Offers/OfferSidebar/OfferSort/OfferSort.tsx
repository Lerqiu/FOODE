import { Divider } from '@mui/material';
import React from 'react';
import OfferSortSelect from './OfferSortSelect/OfferSortSelect';

function ProductOfferFilter() {
  return (
    <>
      <Divider>Sortowanie</Divider>
      <OfferSortSelect />
    </>
  );
}

export default ProductOfferFilter;
