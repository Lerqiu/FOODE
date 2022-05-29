import React from 'react';
import OffersPrice_Button from './OfferPrice.style';

function OffersPrice(props: { price: number }) {
  const { price } = props;

  const getButtonMessage = () => `Cena ${price} pkt`;

  return (
    <OffersPrice_Button
      variant="contained"
      disabled
      sx={{ m: 2 }}
      size="small"
      color="success"
    >
      {getButtonMessage()}
    </OffersPrice_Button>
  );
}

export default OffersPrice;
