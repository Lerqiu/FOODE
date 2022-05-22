import React from 'react';
import { AccountCircle } from '@mui/icons-material';
import { Button, Icon } from '@mui/material';
import ProfileButton from './NavbarProfile.styles';

function NavbarProfile() {
  return (
    <Button variant="text" style={ProfileButton}>
      Profile
      <Icon sx={{ ml: 1 }}>
        <AccountCircle />
      </Icon>
    </Button>
  );
}
export default NavbarProfile;
