import { Box, Container, List, Pagination, PaginationItem, Stack, TextField } from '@mui/material';
import ProductsOffersPagination from './Pagination/ProductsOffersPagination';
import ProductOfertEntry, { IProductOffersEntry } from './ProductOfferEntry/ProductOfferEntry';
import ProductOfferFiltr from './ProductOfferFiltr/ProductOfferFiltr';

const mockData = (): IProductOffersEntry[] => {
  const single = {
    addDate: {
      day: "17",
      month: "05",
      year: "2022"
    },
    expirationDate: {
      day: "18",
      month: "05",
      year: "2022"
    },
    user: { login: "Olek" },
    imgSrc: "https://media.discordapp.net/attachments/966704303119171658/970037795995398144/unknown.png",
    productName: "Jabłko",
    location: {},
    price: { price: "12" }
  }
  let array: IProductOffersEntry[] = []
  for (let i = 0; i < 15; i++)
    array.push({ ...single, id: i.toString() })
  return array
}


const ProductsOffers = () => {
  const listEntry = mockData()

  return (
    <>
      <Box style={{ flex: "1 1 auto", display: "flex", flexFlow: "column", backgroundColor: "#E9EFC0" }}>
        <Box style={{ flex: "1 1 auto", display: "flex" }}>
          <Box>
            <ProductOfferFiltr />
          </Box>

          <Container sx={{ height: '100%' }}>
            <List>
              {
                listEntry.map(data => <ProductOfertEntry data={data} key={data.id} />)
              }
            </List>
          </Container>
        </Box >
        <ProductsOffersPagination />
      </Box>
    </>
  );
}

export default ProductsOffers;