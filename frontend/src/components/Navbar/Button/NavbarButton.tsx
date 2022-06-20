import React from 'react';
import { ButtonProps } from '@mui/material';
import { NavLink } from 'react-router-dom';
import { NavbarButton_NoBackground, NavbarButton_WithBackground } from './NavbarButton.styling';

interface NavbarButtonProps extends ButtonProps {
  renderLink: string
}

function NavbarButton({ renderLink, ...buttonProps }: NavbarButtonProps) {
  return (
    <NavLink
      to={renderLink}
      style={{ textDecoration: 'none' }}
      children={
        ({ isActive }) => (
          isActive
            ? <NavbarButton_WithBackground {...buttonProps} />
            : <NavbarButton_NoBackground {...buttonProps} />
        )
      }
    />
  );
}

export default NavbarButton;
