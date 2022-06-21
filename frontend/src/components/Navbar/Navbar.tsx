import React from 'react';
import {
  Box, Container, Toolbar,
} from '@mui/material';
import { Outlet, useNavigate } from 'react-router-dom';
import NavbarButton from './Button/NavbarButton';
import Paths from '../../conf/Paths';
import Navbar_AppBar from './Navbar.styles';

function Navbar() {
  const navigate = useNavigate();

  const root = Paths;

  const pages = [
    { name: 'Strona główna', link: root.__get },
  ];

  const changeSubSite = (link: string) => () => {
    navigate(link);
  };

  return (
    <Box sx={{ minHeight: '100vh', display: 'flex', flexFlow: 'column' }}>
      <Navbar_AppBar position="static">
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
          </Toolbar>
        </Container>
      </Navbar_AppBar>

      <Outlet />
    </Box>
  );
}

export default Navbar;
