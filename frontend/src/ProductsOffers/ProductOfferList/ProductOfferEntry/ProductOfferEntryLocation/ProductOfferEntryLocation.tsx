import { Chip } from '@mui/material';


export interface IProductOffersLocation {
    location: string
}

const ProductOffersLocation = (props: { data: IProductOffersLocation }) => {
    const location = props.data.location
    return <Chip variant='outlined' label={location} />
}

export default ProductOffersLocation;