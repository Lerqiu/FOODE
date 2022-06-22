import { styled } from '@mui/material/styles';
import MuiButton from '@mui/material/Button';

const OfferDate_Button = styled(MuiButton)(() => ({
  '&.Mui-disabled': {
    backgroundColor: '#83BD75',
    borderRadius: 35,
    color: 'white',
  },
}));

export default OfferDate_Button;
