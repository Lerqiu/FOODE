import React from 'react';
import { Button, ButtonProps } from '@mui/material';
import { NavLink } from 'react-router-dom';
import { NavbarButton_Background, NavbarButton_Raw, NavbarNavLink_style } from './NavbarButton.styles';

interface NavbarButtonProps extends ButtonProps{
  renderLink : string
}

function NavbarButton({ renderLink, ...buttonProps }: NavbarButtonProps) {
  return (
    <NavLink
      to={renderLink}
      style={NavbarNavLink_style}
      children={
        ({ isActive }) => (
          isActive
            ? <Button {...buttonProps} style={NavbarButton_Background} />
            : <Button {...buttonProps} style={NavbarButton_Raw} />
        )
}
    />
  );
}

export default NavbarButton;
