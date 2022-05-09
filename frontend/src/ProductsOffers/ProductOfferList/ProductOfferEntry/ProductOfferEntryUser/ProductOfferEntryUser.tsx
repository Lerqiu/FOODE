import { Chip } from '@mui/material';


export interface IProductOffersUser {
    login: string
}

const ProductOffersUser = (props: { data: IProductOffersUser }) => {
    const data = props.data
    const text = `User: ${data.login}`
    return <Chip variant='outlined' label={text} />
}

export default ProductOffersUser;