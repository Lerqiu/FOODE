import { Button, Container, Grid, Stack, Typography } from "@mui/material"
import ProductOfferEntryDate, { IProductOffersEntryDate } from "./ProductOfferEntryDate"
import ProductOffersLocation, { IProductOffersLocation } from "./ProductOfferEntryLocation"
import ProductOffersPrice, { IProductOffersPrice } from "./ProductOfferEntryPrice"
import ProductOffersUser, { IProductOffersUser } from "./ProductOfferEntryUser"

const mainContainerStyles = { backgroundColor: "#cee8ab", borderRadius: 20 }

export interface IProductOffersEntry {
    id: string
    addDate: IProductOffersEntryDate,
    expirationDate: IProductOffersEntryDate,
    user: IProductOffersUser,
    imgSrc: string,
    productName: string,
    location: IProductOffersLocation,
    price: IProductOffersPrice
}

const ProductOfertEntry = (props: { data: IProductOffersEntry }) => {
    const data = props.data;

    const priceOnClick = () => {
        console.log("Click")
    }

    return (
        //Main container
        < Container style={mainContainerStyles} sx={{ m: 1 }}>
            <Grid container alignItems="center" justifyContent="center" >

                {/*Image */}
                <Grid item xs={2}>
                    <img src={data.imgSrc} style={{ height: '15vh', maxWidth: '100%' }} />
                </Grid>

                {/*Nested contained with product data*/}
                <Grid item xs={10}>
                    <Grid container alignItems="center" justifyContent="center" >
                        {/*First line [sum(xs)=12]*/}
                        <Grid item xs={3}>
                            <ProductOfferEntryDate data={data.addDate} prefix='Data dodania:' />
                        </Grid>
                        <Grid item xs={6} >
                            <Typography variant="h5" noWrap style={{ color: "#4E944F" }} align="center">
                                {data.productName}
                            </Typography>
                        </Grid>
                        <Grid item xs={3}>
                            <ProductOfferEntryDate data={data.expirationDate} prefix='Termin ważności:' />
                        </Grid>

                        {/*Second line [sum(xs)=12]*/}
                        <Grid item xs={3}>
                            <Stack>
                                <ProductOffersUser data={data.user} />
                                <ProductOffersLocation data={data.location} /> {/* Currently empty*/}
                            </Stack>
                        </Grid>
                        <Grid item xs={5} />
                        <Grid item xs={4} >
                            <ProductOffersPrice data={data.price} />
                            <Button variant='contained' color="success" sx={{ m: 2 }} onClick={priceOnClick}>Kup</Button>
                        </Grid>
                    </Grid>
                </Grid>

            </Grid>
        </Container >
    )
}

export default ProductOfertEntry;