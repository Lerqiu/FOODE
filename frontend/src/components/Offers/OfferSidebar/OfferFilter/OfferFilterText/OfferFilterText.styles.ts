import React from 'react';
import { styled } from '@mui/material/styles';
import MuiTextField from '@mui/material/TextField/TextField';

const OfferFilterText_TextField = styled(MuiTextField)(() => ({
  backgroundColor: '#83BD75',
}));

export const OfferFilterText_style_nested: React.CSSProperties = {
  color: 'white',
};

export default OfferFilterText_TextField;
