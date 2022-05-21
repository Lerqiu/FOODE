import React from 'react';

function OfferImage(props: { link: string }) {
  const { link } = props;
  return (
    <img
      src={link}
      style={{ height: '15vh', maxWidth: '100%' }}
      alt="Offer view"
    />
  );
}

export default OfferImage;
