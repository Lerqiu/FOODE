import {
  AppBar, Button, Divider, Stack,
} from '@mui/material';
import React from 'react';
import {
  OfferAdd_style_ButtonStyles,
  OfferFilter_style_ButtonStyles,
  OfferSidebar_style_AppBar,
} from './OfferSidebar.style';
import OfferSortSelect from './OfferSort/OfferSortSelect/OfferSortSelect';
import OfferFilterText from './OfferFilter/OfferFilterText/OfferFilterText';

function OfferSidebar(props: {showModal: any}) {
  const { showModal } = props;
  return (
    <AppBar position="sticky" style={OfferSidebar_style_AppBar}>
      <Stack spacing={1}>
        <Button
          variant="contained"
          color="success"
          sx={{ mx: 2, mt: 2 }}
          style={OfferAdd_style_ButtonStyles}
          onClick={() => showModal(true)}
        >
          Dodaj ofertę
        </Button>

        <Divider>Sortowanie</Divider>
        <OfferSortSelect />

        <Divider>Filtrowanie</Divider>
        <OfferFilterText title="Nazwa użytkownika:" />
        <OfferFilterText title="Nazwa produktu:" />
        <OfferFilterText title="Lokalizacja" />
        <Button variant="contained" color="success" sx={{ mx: 2, mt: 2 }} style={OfferFilter_style_ButtonStyles}>
          Filtruj
        </Button>
      </Stack>
    </AppBar>
  );
}

export default OfferSidebar;
