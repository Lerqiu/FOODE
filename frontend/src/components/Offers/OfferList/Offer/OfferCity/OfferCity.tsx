import { Chip } from '@mui/material';
import ICity from "../../../../../interfaces/city/ICity";

const OfferCity = (props: { city: ICity }) => {
    const {city} = props
    return <Chip variant='outlined' label={city.name} />
}

export default OfferCity;