import {
  AppBar, Button, Divider, Stack,
} from '@mui/material';
import React, { useState } from 'react';
import { useQuery } from 'react-query';
import {
  OfferFilter_style_ButtonStyles,
  OfferSidebar_style_AppBar,
} from './OfferSidebar.style';
import OfferSortSelect from './OfferSort/OfferSortSelect/OfferSortSelect';
import OfferFilterText from './OfferFilter/OfferFilterText/OfferFilterText';
import OfferFilterCitySelect from './OfferFilter/OfferFilterCitySelect/OfferFilterCitySelect';
import ICity from '../../../interfaces/city/ICity';
import CityService from '../../../services/CityService';

function OfferSidebar(props: {showModal: any}) {
  const { showModal } = props;
  const [cities, setCities] = useState<ICity[]>([]);

  useQuery<ICity[], Error>(
    'city-get',
    async () => CityService.findAll(),
    {
      onSuccess: (res) => {
        setCities(res);
      },
      onError: () => {
        setCities([]);
      },
    },
  );

  return (
    <AppBar position="sticky" style={OfferSidebar_style_AppBar}>
      <Stack spacing={1}>
        <Button
          variant="contained"
          color="success"
          sx={{ mx: 2, mt: 2 }}
          onClick={() => showModal(true)}
        >
          Dodaj ofertę
        </Button>

        <Divider>Sortowanie</Divider>
        <OfferSortSelect />

        <Divider>Filtrowanie</Divider>
        <OfferFilterText title="Nazwa użytkownika:" />
        <OfferFilterText title="Nazwa produktu:" />
        <OfferFilterCitySelect cities={cities} />
        <Button variant="contained" color="success" sx={{ mx: 2, mt: 2 }} style={OfferFilter_style_ButtonStyles}>
          Filtruj
        </Button>
      </Stack>
    </AppBar>
  );
}

export default OfferSidebar;
