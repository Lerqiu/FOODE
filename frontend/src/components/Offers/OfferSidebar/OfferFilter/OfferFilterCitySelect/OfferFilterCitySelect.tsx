import React from 'react';
import { MenuItem, Select, Typography } from '@mui/material';
import ICity from '../../../../../interfaces/city/ICity';
import {
  OfferSortSelect_style,
  OfferSortSelect_style_Display,
} from '../../OfferSort/OfferSortSelect/OfferSortSelect.style';

function OfferFilterCitySelect(props: {
  cities: ICity[]
  onChange: (value: string) => void,
  value: string}) {
  const { cities, onChange, value } = props;

  const handleChange = (event: { target: { value: string } }) => {
    const choosenValue = event.target.value;
    onChange(choosenValue);
  };

  return (
    <Select
      labelId="demo-customized-select-label"
      id="demo-customized-select"
      value={value}
      onChange={handleChange}
      variant="filled"
      style={OfferSortSelect_style}
      SelectDisplayProps={{ style: OfferSortSelect_style_Display }}
    >
      <MenuItem value="default" hidden>
        Wybierz miasto
        {' '}
      </MenuItem>
      {cities ? cities.map((_city) => (
        <MenuItem key={_city.id} value={JSON.stringify(_city)}>
          <Typography variant="button">
            {_city.name}
          </Typography>
        </MenuItem>
      )) : ''}
    </Select>
  );
}

export default OfferFilterCitySelect;
