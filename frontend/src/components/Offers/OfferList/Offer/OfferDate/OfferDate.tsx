import React from 'react';
import OfferDate_Button from './OfferDate.styling';

function OfferDate(props: { date: Date, prefix: string }) {
  const { date, prefix } = props;

  const getButtonText = () => `${prefix} ${date.toLocaleDateString()}`;

  return (
    <OfferDate_Button
      variant="contained"
      disabled
      size="small"
    >
      {getButtonText()}
    </OfferDate_Button>
  );
}

export default OfferDate;
