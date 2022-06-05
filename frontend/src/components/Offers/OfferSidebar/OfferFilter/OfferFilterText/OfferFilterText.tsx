import React from 'react';
import { TextField } from '@mui/material';
import { OfferFilterText_style, OfferFilterText_style_nested } from './OfferFilterText.styles';

function OfferFilterText(props: {
  title: string,
  onChange:(value: string) => void,
  defaultValue: string
}) {
  const { title, onChange, defaultValue } = props;
  return (
    <TextField
      label={title}
      defaultValue={defaultValue}
      sx={{ m: 2 }}
      variant="filled"
      color="success"
      onChange={(e) => onChange(e.target.value)}
      InputProps={{ style: OfferFilterText_style_nested }}
      InputLabelProps={{ style: OfferFilterText_style_nested }}
      style={OfferFilterText_style}
    />
  );
}

export default OfferFilterText;
