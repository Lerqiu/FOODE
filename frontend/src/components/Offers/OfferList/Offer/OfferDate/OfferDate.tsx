import React from 'react';
import { Button } from '@mui/material';
import OfferDate_style from './OfferDate.style';

function OfferDate(props: { date: Date, prefix: string }) {
  const { date, prefix } = props;

  const getButtonText = () => `${prefix} ${date}`;

  return (
    <Button
      variant="contained"
      disabled
      size="small"
      style={OfferDate_style}
    >
      {getButtonText()}
    </Button>
  );
}

export default OfferDate;
