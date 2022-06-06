import {
  AppBar, Button, Divider, Stack,
} from '@mui/material';
import React, { useState } from 'react';
import { useQuery, useQueryClient } from 'react-query';
import {
  OfferFilter_style_ButtonStyles,
  OfferSidebar_style_AppBar,
} from './OfferSidebar.style';
import OfferSortSelect from './OfferSort/OfferSortSelect/OfferSortSelect';
import OfferFilterText from './OfferFilter/OfferFilterText/OfferFilterText';
import OfferFilterCitySelect from './OfferFilter/OfferFilterCitySelect/OfferFilterCitySelect';
import ICity from '../../../interfaces/city/ICity';
import CityService from '../../../services/CityService';
import { getOffersPageManagement, saveOffersPageManagement } from '../../../helpers/offerPageStorageHelper';

function OfferSidebar(props: {showModal: any}) {
  const { showModal } = props;

  const initMaxPrice = getOffersPageManagement().priceTo || '';
  const initMinPrice = getOffersPageManagement().priceFrom || '';
  const initProductName = getOffersPageManagement().name || '';
  const initCity = getOffersPageManagement()?.city || 'default';

  const [cities, setCities] = useState<ICity[]>([]);
  const [maxPrice, setMaxPrice] = useState<string>(initMaxPrice);
  const [minPrice, setMinPrice] = useState<string>(initMinPrice);
  const [productName, setProductName] = useState<string>(initProductName);
  const [city, setCity] = useState<string>(initCity);

  const queryClient = useQueryClient();

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

  const handleStorage = () => {
    const offerPageManagement = getOffersPageManagement();
    const offerPageManagementToSave = {
      ...offerPageManagement,
      priceFrom: minPrice,
      priceTo: maxPrice,
      name: productName,
      city: city || '{}',
    };

    saveOffersPageManagement(offerPageManagementToSave);
  };

  const handleQuery = () => {
    queryClient.invalidateQueries('offers-get');
  };

  const handleFilterClick = () => {
    handleStorage();
    handleQuery();
  };

  const clearFilter = () => {
    const offerPageManagement = getOffersPageManagement();
    saveOffersPageManagement({
      sort: offerPageManagement?.sort,
    });
    setMinPrice('');
    setMaxPrice('');
    setProductName('');
    setCity('default');
  };

  const handleClearFilterClick = () => {
    clearFilter();
    handleQuery();
  };

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
        <OfferFilterText
          title="Nazwa produktu:"
          onChange={setProductName}
          value={productName}
        />
        <OfferFilterText
          title="Minimanlna cena:"
          onChange={setMinPrice}
          value={minPrice}
        />
        <OfferFilterText
          title="Maksymalna cena:"
          onChange={setMaxPrice}
          value={maxPrice}
        />
        <OfferFilterCitySelect
          cities={cities}
          onChange={setCity}
          value={city}
        />
        <Button
          variant="contained"
          color="success"
          sx={{ mx: 2, mt: 2 }}
          style={OfferFilter_style_ButtonStyles}
          onClick={() => handleFilterClick()}
        >
          Filtruj
        </Button>
        <Button
          variant="contained"
          color="success"
          sx={{ mx: 2, mt: 2 }}
          style={OfferFilter_style_ButtonStyles}
          onClick={() => handleClearFilterClick()}
        >
          wyczyść filtry
        </Button>
      </Stack>
    </AppBar>
  );
}

export default OfferSidebar;
