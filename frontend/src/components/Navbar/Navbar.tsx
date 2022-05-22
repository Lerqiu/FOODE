import React from 'react';
import {
  AppBar, Box, Container, Toolbar,
} from '@mui/material';
import { Outlet, useNavigate } from 'react-router-dom';
import NavbarButton from './Button/NavbarButton';
import NavbarStyles from './Navbar.styles';
import NavbarProfile from './Profile/NavbarProfile';
import Paths from '../../conf/Paths';

function Navbar() {
  const navigate = useNavigate();

  const root = Paths;

  const pages = [
    { name: 'Strona główna', link: root.__get },
    { name: 'Oferty produktów', link: root.market.__get },
    { name: 'Posiadane produkty', link: root.fridge.__get },
  ];

  const changeSubSite = (link: string) => () => {
    navigate(link);
  };

  return (
    <Box sx={{ minHeight: '100vh', display: 'flex', flexFlow: 'column' }}>
      <AppBar position="static" style={NavbarStyles}>
        <Container maxWidth={false}>
          <Toolbar disableGutters>

            <img src="https://media.discordapp.net/attachments/966704303119171658/970037795995398144/unknown.png" style={{ height: '9.5vh' }} alt="logo" />

            <Box sx={{ flexGrow: 1, display: 'flex' }}>
              {pages.map((page) => (
                <NavbarButton
                  key={page.link}
                  onClick={changeSubSite(page.link)}
                  sx={{ my: 1, display: 'block', mx: 2 }}
                  renderLink={page.link}
                >
                  {page.name}
                </NavbarButton>
              ))}
            </Box>
            <NavbarProfile />
          </Toolbar>
        </Container>
      </AppBar>

      <Outlet />
    </Box>
  );
}

export default Navbar;
