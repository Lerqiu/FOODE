import { styled } from '@mui/material/styles';
import MuiButton from '@mui/material/Button';

const OffersPrice_Button = styled(MuiButton)(() => ({
  '&.Mui-disabled': {
    backgroundColor: '#4E944F',
    color: 'white',
  },
}));

export default OffersPrice_Button;
