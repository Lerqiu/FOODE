import { List } from "@mui/material"
import IOfferView from "../../../interfaces/offer/IOfferView";
import Offer from "./Offer/Offer"

const OfferList = (props: { offers: IOfferView[] }) => {
    const {offers} = props;
    return (
        <List>
            {
               offers.map(offer => <Offer offer={offer} key={offer.id} />)
            }
        </List>
    )
}

export default OfferList;