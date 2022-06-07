import React from 'react';
import { AccountCircle } from '@mui/icons-material';
import { Icon } from '@mui/material';
import Navbar_Profile_Button from './NavbarProfile.styles';

function NavbarProfile() {
  return (
    <Navbar_Profile_Button variant="text">
      Profile
      <Icon sx={{ ml: 1 }}>
        <AccountCircle />
      </Icon>
    </Navbar_Profile_Button>
  );
}
export default NavbarProfile;
