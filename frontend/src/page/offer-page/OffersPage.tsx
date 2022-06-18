import React, { useState } from 'react';
import { Box, Container } from '@mui/material';
import { useQuery, useQueryClient } from 'react-query';
import ProductsOffersPagination from '../../components/Offers/Pagination/ProductsOffersPagination';
import OfferList from '../../components/Offers/OfferList/OfferList';
import IOfferView from '../../interfaces/offer/IOfferView';
import IOffers from '../../interfaces/offer/IOffers';
import OfferSidebar from '../../components/Offers/OfferSidebar/OfferSidebar';
import AddOfferModal from '../../components/Offers/AddOfferModal/AddOfferModal';
import OfferService from '../../services/OfferService';
import { OffersPage_MainContainer, OffersPage_SecondaryContainer } from './OffersPage.style';
import { IPage, IPaginationRaw } from '../../interfaces/pagination/IPagination';
import { getDefaultPage } from '../../helpers/offersPagination';
import IOffersResponse from '../../interfaces/offersResponse/offersResponse';

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

const mapPage = (res: IPaginationRaw): IPage => ({
  pagesCount: res.totalPages <= 0 ? 1 : res.totalPages,
  currentPage: res.number + 1,
  pageSize: res.size,
});

function OffersPage() {
  const reset: [IOfferView[], IPage] = [[], getDefaultPage()];

  const [showModal, setShowModal] = useState(false);
  const [[offers, page], setOffers] = useState<[IOfferView[], IPage]>(reset);

  const setPage = (_page: IPage) => {
    setOffers([offers, _page]);
  };

  useQuery<IOffersResponse, Error>(
    ['offers-get', { page: page.currentPage }],
    async () => OfferService.findAll(page),
    {
      onSuccess: (res) => {
        setOffers([mapOffers(res), mapPage(res)]);
      },
      onError: () => {
        setOffers(reset);
      },
    },
  );

  if (page.currentPage > page.pagesCount) { setOffers([offers, { ...page, currentPage: page.pagesCount }]); }

  return (
    <OffersPage_MainContainer>
      <OffersPage_SecondaryContainer>
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
      </OffersPage_SecondaryContainer>
      <ProductsOffersPagination doRender={setPage} state={page} />
    </OffersPage_MainContainer>
  );
}

export default OffersPage;
