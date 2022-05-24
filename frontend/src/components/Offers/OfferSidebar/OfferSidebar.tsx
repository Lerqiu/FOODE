import { AppBar, Stack } from '@mui/material';
import React from 'react';
import { OfferSidebar_style_AppBar } from './OfferSidebar.style';
import OfferSort from './OfferSort/OfferSort';
import OfferFilter from './OfferFilter/OfferFilter';
import OfferAdd from './OfferAdd/OfferAdd';

function OfferSidebar(props: {showModal: any}) {
  const { showModal } = props;
  return (
    <AppBar position="sticky" style={OfferSidebar_style_AppBar}>
      <Stack spacing={1}>
        <OfferAdd showModal={showModal} />
        <OfferSort />
        <OfferFilter />
      </Stack>
    </AppBar>
  );
}

export default OfferSidebar;
