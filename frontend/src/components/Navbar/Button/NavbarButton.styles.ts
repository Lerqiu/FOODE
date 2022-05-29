import { styled } from '@mui/material/styles';
import MuiButton from '@mui/material/Button';

// https://mui-treasury.com/styles/button/

export const NavbarButton_WithBackground = styled(MuiButton)(() => ({
  minWidth: 'fixContent',
  transition: '0.3s cubic-bezier(.47,1.64,.41,.8)',
  background:
    /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
    'linear-gradient(to right, #83BD75, #4E944F)',
  '&:hover': {
    transform: 'scale(1.1)',
  },
  borderRadius: 50,
  color: 'white',
  fontSize: 15,
  fontWeight: 700,
}));

export const NavbarButton_NoBackground = styled(MuiButton)(() => ({
  minWidth: 'fixContent',
  borderRadius: 50,
  color: 'white',
  fontSize: 15,
  fontWeight: 1000,
}));
