import { Chip } from '@mui/material';
import IOfferUser from "../../../../../interfaces/offer/user/IOfferUser";


const OffersUser = (props: { user: IOfferUser }) => {
    const {user} = props
    const text = `User: ${user.login}`
    return <Chip variant='outlined' label={text} />
}

export default OffersUser;