import { Button, Divider, Stack } from '@mui/material';
import React from 'react';
import { OfferFilter_style_ButtonStyles } from './OfferFilter.style';
import OfferFilterText from './OfferFilterText/OfferFilterText';

function OfferFilter() {
  return (
    <Stack spacing={1}>
      <Divider>Filtrowanie</Divider>
      <OfferFilterText title="Nazwa użytkownika:" />
      <OfferFilterText title="Nazwa produktu:" />
      <OfferFilterText title="Lokalizacja" />
      <Button variant="contained" color="success" sx={{ mx: 2, mt: 2 }} style={OfferFilter_style_ButtonStyles}>
        Filtruj
      </Button>
    </Stack>

  );
}

export default OfferFilter;
