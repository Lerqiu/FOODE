import React, { useCallback, useEffect, useState } from 'react';
import { Box, Container } from '@mui/material';
import ProductsOffersPagination from '../../components/Offers/Pagination/ProductsOffersPagination';
import ProductOfferFilter from '../../components/Offers/ProductOfferFilter/ProductOfferFilter';
import OfferList from '../../components/Offers/OfferList/OfferList';
import {
  OffersPage_style_contextContainer,
  OffersPage_style_mainContainer,
} from './OffersPage.style';
import IOffer from '../../interfaces/offer/IOffer';
import IOfferView from '../../interfaces/offer/IOfferView';
import getOffersConnection from '../../components/Offers/Axios/Connection';

const mapOffers = (_offers: IOffer[]): IOfferView[] => {
  const IMAGE_PLACEHOLDER_URL = 'https://media.discordapp.net/attachments/966704303119171658/970037795995398144/unknown.png';

  const result = _offers.map((_offer) => (
    {
      ..._offer,
      imgSrc: IMAGE_PLACEHOLDER_URL,
      expirationDate: _offer.product.expirationDate,
    }));

  return result;
};

function OffersPage() {
  const [offers, setOffers] = useState<IOfferView[]>([]);

  const api_prefix = 'offers';
  const default_id = 'cityId=1';
  const url_prefix = process.env.REACT_APP_URL_PREFIX;
  const client = getOffersConnection();

  const getOffers = useCallback(() => {
    client.get(
      `${url_prefix}${api_prefix}?${default_id}`,
    )
      .then((response) => setOffers(mapOffers(response.data.content)))
      .catch(console.error);
  }, [api_prefix, default_id, url_prefix, client]);

  useEffect(() => {
    getOffers();
  }, [getOffers]);

  return (
    <Box style={OffersPage_style_mainContainer}>
      <Box style={OffersPage_style_contextContainer}>
        <Box>
          <ProductOfferFilter />
        </Box>
        <Container sx={{ height: '100%' }}>
          <OfferList offers={offers} />
        </Container>
      </Box>
      <ProductsOffersPagination />
    </Box>
  );
}

export default OffersPage;
