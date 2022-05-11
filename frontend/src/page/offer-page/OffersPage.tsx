import {Box, Container} from '@mui/material';
import ProductsOffersPagination from '../../components/Offers/Pagination/ProductsOffersPagination';
import ProductOfferFiltr from '../../components/Offers/ProductOfferFilter/ProductOfferFilter';
import OfferList from '../../components/Offers/OfferList/OfferList';
import {
    OffersPage_style_contextContainer,
    OffersPage_style_mainContainer,
} from './OffersPage.style';
import {useCallback, useEffect, useState} from "react";
import axios from 'axios';
import IOffer from "../../interfaces/offer/IOffer";

const OffersPage = () => {

    const [offers, setOffers] = useState<any>([])

    const URL_PREFIX = `http://localhost:8080/api/`;

    const OFFER_API_PREFIX = `offers`;

    const DEFAULT_CITY_ID = `cityId=1`;

    const IMAGE_PLACEHOLDER_URL = `https://media.discordapp.net/attachments/966704303119171658/970037795995398144/unknown.png`;

    const client = axios.create({
        baseURL: URL_PREFIX
    });

    const getOffers = useCallback(() => {
        client.get(
            `${URL_PREFIX}${OFFER_API_PREFIX}?${DEFAULT_CITY_ID}`,
        )
            .then((response) => setOffers(mapOffers(response.data.content)))
            .catch((reason) => console.log(reason));
    }, []);

    const mapOffers = (offers: IOffer[]) => {
        return offers.map(offer => ({...offer, imgSrc: IMAGE_PLACEHOLDER_URL }))
    }

    useEffect(() => {
        getOffers();
    }, [getOffers]);

    return (
        <Box style={OffersPage_style_mainContainer}>
           <Box style={OffersPage_style_contextContainer}>
               <Box>
                   <ProductOfferFiltr/>
               </Box>
               <Container sx={{height: '100%'}}>
                   <OfferList offers={offers}/>
               </Container>
           </Box>
           <ProductsOffersPagination/>
        </Box>
    );
}

export default OffersPage;