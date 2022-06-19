import React from 'react';
import { Chip } from '@mui/material';
import IOfferUser from '../../../../../interfaces/offer/user/IOfferUser';

function OffersUser(props: { user: IOfferUser }) {
  const { user } = props;
  const text = `User: ${user.login}`;
  return <Chip variant="filled" label={text} />;
}

export default OffersUser;
