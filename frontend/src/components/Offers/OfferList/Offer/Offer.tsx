import React from 'react';
import {
  Box, Button, Grid,
} from '@mui/material';
import IOfferView from '../../../../interfaces/offer/IOfferView';
import OfferImage from './OfferImage/OfferImage';
import OfferUser from './OfferUser/OfferUser';
import OffersPrice from './OfferPrice/OfferPrice';
import OfferDate from './OfferDate/OfferDate';
import OfferProduct from './OfferProduct/OfferProduct';
import OfferCity from './OfferCity/OfferCity';
import Offers_Container from './Offer.style';

function Offer(props: { offer: IOfferView }) {
  const { offer } = props;

  const priceOnClick = () => {
    console.log('Click');
    console.log(offer);
  };

  return (
    // Main container
    <Offers_Container sx={{ m: 1 }}>
      <Grid container alignItems="center" justifyContent="center">

        <Grid item xs={2}>
          <OfferImage link={offer.imgSrc} />
        </Grid>

        {/* Nested contained with product data */}
        <Grid item xs={10}>
          <Grid container alignItems="center" justifyContent="center">
            {/* First line [sum(xs)=12] */}
            <Grid item xs={3}>
              <OfferDate date={offer.date} prefix="Data dodania:" />
            </Grid>
            <Grid item xs={6}>
              <OfferProduct product={offer.product} />
            </Grid>
            <Grid item xs={3}>
              <OfferDate date={offer.expirationDate} prefix="Termin ważności:" />
            </Grid>

            {/* Second line [sum(xs)=12] */}
            <Grid item xs={8}>
              <OfferUser user={offer.userOutput} />
              <OfferCity city={offer.city} />
            </Grid>
            <Grid item xs={4}>
              <Box style={{ justifyContent: 'flex-end', display: 'flex' }}>
                <Button variant="contained" color="success" sx={{ m: 2 }} onClick={priceOnClick}>Kup</Button>
                <OffersPrice price={offer.price} />
              </Box>
            </Grid>
          </Grid>
        </Grid>
      </Grid>
    </Offers_Container>
  );
}

export default Offer;
