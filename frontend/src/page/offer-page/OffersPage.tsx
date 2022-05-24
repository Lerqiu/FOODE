import React, { useState } from 'react';
import { Box, Container } from '@mui/material';
import { useQuery } from 'react-query';
import ProductsOffersPagination from '../../components/Offers/Pagination/ProductsOffersPagination';
import OfferList from '../../components/Offers/OfferList/OfferList';
import { OffersPage_style_contextContainer, OffersPage_style_mainContainer } from './OffersPage.style';
import IOfferView from '../../interfaces/offer/IOfferView';
import IOffers from '../../interfaces/offer/IOffers';
import OfferSidebar from '../../components/Offers/OfferSidebar/OfferSidebar';
import AddOfferModal from '../../components/Offers/AddOfferModal/AddOfferModal';
import OfferService from '../../services/OfferService';

const mapOffers = (_offers: IOffers): IOfferView[] => {
  const IMAGE_PLACEHOLDER_URL = 'https://media.discordapp.net/attachments/966704303119171658/970037795995398144/unknown.png';

  return _offers.content.map((_offer) => (
    {
      ..._offer,
      imgSrc: IMAGE_PLACEHOLDER_URL,
      expirationDate: new Date(_offer.product.expirationDate),
      date: new Date(_offer.date),
    }));
};

function OffersPage() {
  const [showModal, setShowModal] = useState(false);
  const [offers, setOffers] = useState<IOfferView[]>([]);

  useQuery<IOffers, Error>(
    'offers-get',
    async () => OfferService.findAll(),
    {
      onSuccess: (res) => {
        setOffers(mapOffers(res));
      },
      onError: () => {
        setOffers([]);
      },
    },
  );

  return (
    <Box style={OffersPage_style_mainContainer}>
      <Box style={OffersPage_style_contextContainer}>
        <Box>
          <OfferSidebar showModal={setShowModal} />
        </Box>
        <Container sx={{ height: '100%' }}>
          <AddOfferModal
            show={showModal}
            onHide={() => setShowModal(false)}
          />
          <OfferList offers={offers} />
        </Container>
      </Box>
      <ProductsOffersPagination />
    </Box>
  );
}

export default OffersPage;
