import { AppBar, Button, createTheme, Divider, Stack, TextField, ThemeProvider } from "@mui/material";
import ProductOfferFiltrSelect from "./ProductOfferFiltrSelect";
import ProductOfferFiltrText from "./ProductOfferFiltrText";

const buttonStyles = { backgroundColor: "#4E944F" }

const ProductOfferFiltr = () => {
    return (
        <AppBar position='sticky' style={{ backgroundColor: "#B4E197" }}>
            <Stack spacing={1}>
                <Button variant='contained' color="success" sx={{ mx: 2, mt: 2 }} style={buttonStyles}>Dodaj ofertę</Button>
                <Divider>Sortowanie</Divider>
                <ProductOfferFiltrSelect />
                <Divider>Filtrowanie</Divider>
                <ProductOfferFiltrText title="Nazwa użytkownika:" />
                <ProductOfferFiltrText title="Nazwa produktu:" />
                {/* <ProductOfferFiltrText title="Lokalizacja" /> */}
                <Button variant='contained' color="success" sx={{ mx: 2, mt: 2 }} style={buttonStyles}>Filtruj</Button>
            </Stack>
        </AppBar>
    )
}

export default ProductOfferFiltr;