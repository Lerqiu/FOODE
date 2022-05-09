import { Box, Button, Container, Grid } from "@mui/material"
import IProductOffersEntry from "./IProductOfferEntry"
import { ProductOfferEntry_style } from "./ProductOfferEntry.style"
import ProductOfferEntryDate from "./ProductOfferEntryDate/ProductOfferEntryDate"
import ProductOfferEntryImage from "./ProductOfferEntryImage/ProductOfferEntryImage"
import ProductOffersLocation from "./ProductOfferEntryLocation/ProductOfferEntryLocation"
import ProductOffersPrice from "./ProductOfferEntryPrice/ProductOfferEntryPrice"
import ProductOfferEntryTitle from "./ProductOfferEntryTitle/ProductOfferEntryTitle"
import ProductOffersUser from "./ProductOfferEntryUser/ProductOfferEntryUser"


const ProductOfferEntry = (props: { data: IProductOffersEntry }) => {
    const data = props.data;

    const priceOnClick = () => {
        console.log("Click")
    }

    return (
        //Main container
        < Container style={ProductOfferEntry_style} sx={{ m: 1 }}>
            <Grid container alignItems="center" justifyContent="center" >

                <Grid item xs={2}>
                    <ProductOfferEntryImage link={data.imgSrc} />
                </Grid>

                {/*Nested contained with product data*/}
                <Grid item xs={10}>
                    <Grid container alignItems="center" justifyContent="center" >
                        {/*First line [sum(xs)=12]*/}
                        <Grid item xs={3}>
                            <ProductOfferEntryDate date={data.addDate} prefix='Data dodania:' />
                        </Grid>
                        <Grid item xs={6} >
                            <ProductOfferEntryTitle title={data.productName} />
                        </Grid>
                        <Grid item xs={3}>
                            <ProductOfferEntryDate date={data.expirationDate} prefix='Termin ważności:' />
                        </Grid>

                        {/*Second line [sum(xs)=12]*/}
                        <Grid item xs={8}>
                            <ProductOffersUser data={data.user} />
                            <ProductOffersLocation data={data.location} />
                        </Grid>
                        <Grid item xs={4}>
                            <Box style={{ justifyContent: "flex-end", display: "flex" }}>
                                
                                <Button variant='contained' color="success" sx={{ m: 2 }} onClick={priceOnClick}>Kup</Button>
                                <ProductOffersPrice data={data.price} />
                            </Box>
                        </Grid>
                    </Grid>
                </Grid>

            </Grid>
        </Container >
    )
}

export default ProductOfferEntry;