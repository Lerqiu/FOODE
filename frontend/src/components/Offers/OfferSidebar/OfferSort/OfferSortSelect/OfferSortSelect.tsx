import React, { useState } from 'react';
import { MenuItem, Select, Typography } from '@mui/material';
import { useQueryClient } from 'react-query';
import { OfferSortSelect_style, OfferSortSelect_style_Display } from './OfferSortSelect.style';
import { getOffersPageManagement, saveOffersPageManagement } from '../../../../../helpers/offerPageStorageHelper';

function OfferSortSelect() {
  const initialSelect = getOffersPageManagement().sort || 'date,desc';

  const [selectIndex, setSelectIndex] = useState(initialSelect);

  const queryClient = useQueryClient();

  const handleStorage = (value: string) => {
    const offerPageManagement = getOffersPageManagement();
    const offerPageManagementToSave = {
      ...offerPageManagement,
      sort: value,
    };

    saveOffersPageManagement(offerPageManagementToSave);
  };

  const handleQuery = () => {
    queryClient.invalidateQueries('offers-get');
  };

  const handleChange = (event: { target: { value: string } }) => {
    const choosenValue = event.target.value;
    setSelectIndex(choosenValue);
    handleStorage(choosenValue);
    handleQuery();
  };

  return (
    <Select
      labelId="demo-customized-select-label"
      id="demo-customized-select"
      value={selectIndex}
      onChange={handleChange}
      variant="filled"
      style={OfferSortSelect_style}
      SelectDisplayProps={{ style: OfferSortSelect_style_Display }}
    >
      <MenuItem value="date,desc"><Typography variant="button">Data dodania</Typography></MenuItem>
      <MenuItem value="product.expirationDate,asc"><Typography variant="button">Data ważności</Typography></MenuItem>
      <MenuItem value="price,desc"><Typography variant="button">Cena malejąco</Typography></MenuItem>
      <MenuItem value="price,asc"><Typography variant="button">Cena rosnąco</Typography></MenuItem>
    </Select>
  );
}

export default OfferSortSelect;
