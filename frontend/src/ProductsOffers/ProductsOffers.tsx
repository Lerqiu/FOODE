import { Box, Button, Container, Pagination, PaginationItem, Paper, Stack } from '@mui/material';
import React, { useEffect, useRef, useState } from 'react'
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';

const MockDiv = () => {
  return (
    < div style={{ height: '200px', width: '500px', backgroundColor: 'grey', margin: '10px' }}>
      MOCK
    </div >)
}

const ProductsOffersPagination = () => {
  return (
    <Box
      display="flex"
      width='100%'
      bgcolor="lightgreen"
      alignItems="center"
      justifyContent="center"
    >
      <Pagination
        count={5}
        renderItem={(item) => (
          <PaginationItem
            components={{ previous: ArrowBackIcon, next: ArrowForwardIcon }}
            {...item}
          />
        )}
      />
    </Box>
  );
}

const ProductsOffers = () => {
  return (
    <Stack direction="row" sx={{ height: '100%' }}>
      <Container sx={{ width: '30vw' }} >
        <Paper sx={{ overflow: 'auto', height: "90.5vh" , backgroundColor: 'red' }}>

        </Paper>
      </Container>
      <Container sx={{ height: "100%" }} >
        <Paper sx={{ overflow: 'scroll', height: "90.5vh" }}>
          <MockDiv />
          <MockDiv />
          <MockDiv />
          <MockDiv />
          <MockDiv />
          <MockDiv />
          <MockDiv />
          <ProductsOffersPagination />
        </Paper>
      </Container>
    </Stack >
  );
}

export default ProductsOffers;