import React from 'react';
import OfferFilterText_TextField, { OfferFilterText_style_nested } from './OfferFilterText.styles';

function OfferFilterText(props: {
  title: string,
  // eslint-disable-next-line no-unused-vars
  onChange:(value: string) => void,
  value: string
}) {
  const {
    title, onChange, value,
  } = props;
  return (
    <OfferFilterText_TextField
      label={title}
      sx={{ m: 2 }}
      variant="filled"
      color="success"
      value={value}
      onChange={(e) => onChange(e.target.value)}
      InputProps={{ style: OfferFilterText_style_nested }}
      InputLabelProps={{ style: OfferFilterText_style_nested }}
    />
  );
}

export default OfferFilterText;
