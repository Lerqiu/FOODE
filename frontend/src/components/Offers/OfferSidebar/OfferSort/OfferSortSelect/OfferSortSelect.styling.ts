import { styled } from '@mui/material/styles';
import MuiSelect from '@mui/material/Select';

const OfferSidebarSort_Select = styled(MuiSelect)(() => ({
  borderRadius: 4,
  backgroundColor: '#4E944F',
  border: '1px solid #ced4da',
  fontSize: 16,
  margin: '10px',
  borderColor: '#83BD75',
  color: 'white',
  '.MuiSelect-select': {
    padding: 5,
  },
}));

export default OfferSidebarSort_Select;
