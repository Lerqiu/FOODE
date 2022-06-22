import React from 'react';
import OffersPrice_Button from './OfferPrice.styling';

function OffersPrice(props: { price: number }) {
  const { price } = props;

  const getButtonMessage = () => `${price} pkt`;

  return (
    <OffersPrice_Button
      variant="contained"
      disabled
      sx={{ m: 2 }}
      color="success"
    >
      {getButtonMessage()}
    </OffersPrice_Button>
  );
}

export default OffersPrice;
