import React from 'react';
import { Button } from '@mui/material';
import OffersPrice_style from './OffersPrice.style';

function OffersPrice(props: { price: number }) {
  const { price } = props;

  const getButtonMessage = () => `Cena ${price} pkt`;

  return (
    <Button
      variant="contained"
      disabled
      sx={{ m: 2 }}
      size="small"
      color="success"
      style={OffersPrice_style}
    >
      {getButtonMessage()}
    </Button>
  );
}

export default OffersPrice;
