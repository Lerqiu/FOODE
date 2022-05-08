import { Chip } from '@mui/material';


export interface IProductOffersLocation { 
    location: string
}

const ProductOffersLocation = (props: { data: IProductOffersLocation }) => {
    const data = props.data
    return <Chip variant='outlined' label={props.data.location} size="small"/>
}

export default ProductOffersLocation;