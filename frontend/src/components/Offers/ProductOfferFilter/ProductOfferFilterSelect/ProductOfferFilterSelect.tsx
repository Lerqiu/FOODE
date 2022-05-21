import React, { useState } from 'react';
import { MenuItem, Select, Typography } from '@mui/material';
import { ProductOfferFilterSelect_style, ProductOfferFilterSelect_style_Display } from './ProductOfferFilterSelect.style';

function ProductOfferFilterSelect() {
  const [selectIndex, setSelectIndex] = useState('0');
  const handleChange = (event: { target: { value: string } }) => {
    setSelectIndex(event.target.value);
  };
  return (
    <Select
      labelId="demo-customized-select-label"
      id="demo-customized-select"
      value={selectIndex}
      onChange={handleChange}
      variant="filled"
      style={ProductOfferFilterSelect_style}
      SelectDisplayProps={{ style: ProductOfferFilterSelect_style_Display }}
    >
      <MenuItem value={0}><Typography variant="button">Data dodania</Typography></MenuItem>
      <MenuItem value={10}><Typography variant="button">Data ważności</Typography></MenuItem>
      <MenuItem value={20}><Typography variant="button">Cena malejąco</Typography></MenuItem>
      <MenuItem value={30}><Typography variant="button">Cena rosnąco</Typography></MenuItem>
    </Select>
  );
}

export default ProductOfferFilterSelect;
