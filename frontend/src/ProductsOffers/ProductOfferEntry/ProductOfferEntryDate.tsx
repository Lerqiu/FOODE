import { Button} from '@mui/material';


export interface IProductOffersEntryDate {
    day: string,
    month: string,
    year: string
}

const ProductOfferEntryDate = (props: { data: IProductOffersEntryDate, prefix: string }) => {
    const data = props.data
    const text = `${props.prefix} ${data.day}-${data.month}-${data.year}`

    return (
        <Button
            variant="contained"
            disabled
            size="small"
            style={{ borderRadius: 35, backgroundColor: "#83BD75", color: "white" }}
        >{text}</Button>
    )
}

export default ProductOfferEntryDate;