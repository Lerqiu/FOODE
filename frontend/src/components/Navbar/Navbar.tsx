import React from 'react';
import {
  Box, Container, Toolbar,
} from '@mui/material';
import { Outlet, useNavigate } from 'react-router-dom';
import NavbarButton from './Button/NavbarButton';
import SubSitesPaths from '../../conf/Paths';
import Navbar_AppBar from './Navbar.styling';

function Navbar() {
  const navigate = useNavigate();

  const root = SubSitesPaths;

  const pages = [
    { name: 'Strona główna', link: root.__get },
  ];

  const generateChangeSubSite_callBack = (link: string) => () => {
    navigate(link);
  };

  return (
    <Box sx={{ minHeight: '100vh', display: 'flex', flexFlow: 'column' }}>
      <Navbar_AppBar position="static">
        <Container maxWidth={false}>
          <Toolbar disableGutters>

            <img src={`${process.env.PUBLIC_URL}default_offer_img.png`} style={{ height: '9.5vh' }} alt="logo" />

            <Box sx={{ flexGrow: 1, display: 'flex' }}>
              {pages.map((page) => (
                <NavbarButton
                  key={page.link}
                  onClick={generateChangeSubSite_callBack(page.link)}
                  sx={{ my: 1, display: 'block', mx: 2 }}
                  renderLink={page.link}
                >
                  {page.name}
                </NavbarButton>
              ))}
            </Box>
          </Toolbar>
        </Container>
      </Navbar_AppBar>

      <Outlet />
    </Box>
  );
}

export default Navbar;
