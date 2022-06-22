import React from 'react';
import {
  Box, Button, Grid,
} from '@mui/material';
import { useQueryClient } from 'react-query';
import IOfferView from '../../../../interfaces/offer/IOfferView';
import OfferImage from './OfferImage/OfferImage';
import OfferUser from './OfferUser/OfferUser';
import OffersPrice from './OfferPrice/OfferPrice';
import OfferDate from './OfferDate/OfferDate';
import OfferProduct from './OfferProduct/OfferProduct';
import OfferCity from './OfferCity/OfferCity';
import Offers_Container from './Offer.styling';
import OfferService from '../../../../services/OfferService';
import OfferDescription from './OfferDescription/OfferDescription';

function Offer(props: { offer: IOfferView }) {
  const { offer } = props;
  const queryClient = useQueryClient();

  const deleteOnClick = () => {
    OfferService.deleteOne(offer).then(() => queryClient.invalidateQueries('offers-get'));
  };

  return (
    // Main container
    <Offers_Container sx={{ m: 1, margin: '30px' }}>
      <Grid container alignItems="center" justifyContent="center">

        <Grid item xs={2}>
          <OfferImage link={offer.imgSrc} />
        </Grid>

        {/* Nested contained with product data */}
        <Grid item xs={10}>
          <Grid container spacing={2} alignItems="center" justifyContent="center">
            <Grid item xs={3}>
              <OfferDate date={offer.date} prefix="Data dodania:" />
            </Grid>
            <Grid item xs={6}>
              <OfferProduct product={offer.product} />
            </Grid>
            <Grid item xs={3}>
              <OfferDate date={offer.expirationDate} prefix="Termin ważności:" />
            </Grid>

            <Grid item xs={3}>
              <Grid container>
                <Grid item xs={6}>
                  <OfferUser user={offer.userOutput} />
                </Grid>
                <Grid item xs={6}>
                  <OfferCity city={offer.city} />
                </Grid>
              </Grid>
            </Grid>
            <Grid item xs={6}>
              <OfferDescription description={offer.description} />
            </Grid>
            <Grid item xs={3}>
              <Grid container justifyContent="space-between">
                <Grid item xs={6}>
                  <Button variant="contained" color="success" sx={{ m: 2 }} onClick={deleteOnClick}>Usuń</Button>
                </Grid>
                <Grid item xs={6}>
                  <OffersPrice price={offer.price} />
                </Grid>
              </Grid>
            </Grid>
          </Grid>
        </Grid>
      </Grid>
    </Offers_Container>
  );
}

export default Offer;
