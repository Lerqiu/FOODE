import { Button } from '@mui/material';
import React from 'react';
import { OfferAdd_style_ButtonStyles } from './OfferAdd.style';

function OfferAdd(props: {showModal: any}) {
  const { showModal } = props;
  return (
    <div>
      <Button
        variant="contained"
        color="success"
        sx={{ mx: 2, mt: 2 }}
        style={OfferAdd_style_ButtonStyles}
        onClick={() => showModal(true)}
      >
        Dodaj ofertę
      </Button>
    </div>

  );
}

export default OfferAdd;
