import { Container, Grid, List, ListItem, ListItemButton, ListItemText, Paper } from '@mui/material';
import React, { useEffect, useRef, useState } from 'react'

const MockDiv = () => {
  return (
    < div style={{ height: '200px', width: '500px', backgroundColor: 'grey' ,margin:'10px'}}>
      MOCK
    </div >)
}

const ProductsOffers = () => {

  const parentRef = useRef<HTMLInputElement | null>(null);;
  const [height, setHeight] = useState(0);

  useEffect(() => {
    if (parentRef.current && height === 0) {

      let parentHeight = parentRef.current.offsetHeight;
      setHeight(parentHeight)
    }
  }, [parentRef]);

  return (
    <Grid container sx={{ display: 'flex', flexGrow: '1' }} spacing={0} padding={0}>
      <Grid item xs={2} >
        <Container sx={{ background: "red", height: '100%' }}>
          Filtry
        </Container>
      </Grid>
      <Grid item xs={10} >
        <Container sx={{ background: "pink", height: '100%' }} ref={parentRef}>
          <Paper sx={{ overflow: "auto", height: `${height}px` }}>
          <MockDiv/>
          <MockDiv/>
          <MockDiv/>
          <MockDiv/>
          <MockDiv/>
          <MockDiv/>
          <MockDiv/>
        </Paper>
      </Container>
    </Grid>
    </Grid >
  );
}

export default ProductsOffers;