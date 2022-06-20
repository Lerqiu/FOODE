import React from 'react';
import { MenuItem, Typography } from '@mui/material';
import ICity from '../../../../../interfaces/city/ICity';
import OfferFilterCity_Select from './OfferFilterCitySelect.styling';

function OfferFilterCitySelect(props: {
  cities: ICity[]
  // eslint-disable-next-line no-unused-vars
  onChange: (value: string) => void,
  value: string}) {
  const { cities, onChange, value } = props;

  const handleChange = (event: { target: { value: string } }) => {
    const choosenValue = event.target.value;
    onChange(choosenValue);
  };

  return (
    <OfferFilterCity_Select
      labelId="demo-customized-select-label"
      id="demo-customized-select"
      value={value}
      onChange={(e: any) => handleChange(e)}
      variant="filled"
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
    </OfferFilterCity_Select>
  );
}

export default OfferFilterCitySelect;
