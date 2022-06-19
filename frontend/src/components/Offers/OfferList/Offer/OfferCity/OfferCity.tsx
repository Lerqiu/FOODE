import React from 'react';
import { Chip } from '@mui/material';
import ICity from '../../../../../interfaces/city/ICity';

function OfferCity(props: { city: ICity }) {
  const { city } = props;
  return <Chip variant="filled" label={city.name} />;
}

export default OfferCity;
