import { AppBar, Button, Divider, Stack } from "@mui/material";
import { ProductOfferFilter_style_AppBar, ProductOfferFilter_style_ButtonStyles } from "./ProductOfferFilter.style";
import ProductOfferFiltrSelect from "./ProductOfferFilterSelect/ProductOfferFilterSelect";
import ProductOfferFilterText from "./ProductOfferFilterText/ProductOfferFilterText";


const ProductOfferFilter = () => {
    return (
        <AppBar position='sticky' style={ProductOfferFilter_style_AppBar}>
            <Stack spacing={1}>
                <Button variant='contained' color="success" sx={{ mx: 2, mt: 2 }} style={ProductOfferFilter_style_ButtonStyles}>
                    Dodaj ofertę
                </Button>

                <Divider>Sortowanie</Divider>
                <ProductOfferFiltrSelect />

                <Divider>Filtrowanie</Divider>
                <ProductOfferFilterText title="Nazwa użytkownika:" />
                <ProductOfferFilterText title="Nazwa produktu:" />
                <ProductOfferFilterText title="Lokalizacja" />
                <Button variant='contained' color="success" sx={{ mx: 2, mt: 2 }} style={ProductOfferFilter_style_ButtonStyles}>
                    Filtruj
                </Button>
            </Stack>
        </AppBar>
    )
}

export default ProductOfferFilter;