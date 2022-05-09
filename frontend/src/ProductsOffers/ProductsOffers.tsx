import { Box, Container} from '@mui/material';
import ProductsOffersPagination from './Pagination/ProductsOffersPagination';
import ProductOfferFiltr from './ProductOfferFilter/ProductOfferFilter';
import ProductOfferList from './ProductOfferList/ProductOfferList';
import { ProductsOffers_style_contextContainer, ProductsOffers_style_mainContainer } from './ProductsOffers.style';
import ProductsOffers_MockData from './__ProductsOffers.MockData';

const ProductsOffers = () => {
  const listEntry = ProductsOffers_MockData()

  return (
    <Box style={ProductsOffers_style_mainContainer}>
      <Box style={ProductsOffers_style_contextContainer}>
        <Box>
          <ProductOfferFiltr />
        </Box>
        <Container sx={{ height: '100%' }}>
          <ProductOfferList list={listEntry} />
        </Container>
      </Box >
      <ProductsOffersPagination />
    </Box>
  );
}

export default ProductsOffers;