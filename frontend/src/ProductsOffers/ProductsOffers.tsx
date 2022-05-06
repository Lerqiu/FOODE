import { AppBar, Box, Button, Container, Grid, List, Pagination, PaginationItem, Paper, Stack, TextField } from '@mui/material';
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
      flex="0 1 auto"
      width='100%'
      height="fit-content"
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
    <>
      <Grid container spacing={0} padding={0} style={{ flex: "1 1 auto", display: "flex" }}>
        <Grid item xs={2} >
          <AppBar position='sticky' >
            <Container sx={{ background: "grey" }} >
            <TextField id="filled-basic" label="Filled" variant="filled" />
            <TextField id="filled-basic" label="Filled" variant="filled" />
            <TextField id="filled-basic" label="Filled" variant="filled" />
            <TextField id="filled-basic" label="Filled" variant="filled" />
            <TextField id="filled-basic" label="Filled" variant="filled" />
          </Container>
          </AppBar>
        </Grid>
        <Grid item xs={10} >
          <Container sx={{ background: "pink", height: '100%' }}>
            <List>
              <MockDiv />
              <MockDiv />
              <MockDiv />
              <MockDiv />
              <MockDiv />
              <MockDiv />
              <MockDiv />
            </List>
          </Container>
        </Grid>
      </Grid >
      <ProductsOffersPagination />
    </>
  );
}

export default ProductsOffers;