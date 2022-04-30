import { AppBar, Box, Container, Toolbar } from '@mui/material';
import { Outlet, useLocation, useNavigate } from 'react-router-dom';
import NavbarButton from './Button/NavbarButton';
import NavbarStyles from './Navbar.styles'
import NavbarProfil from './Profil/NavbarProfil';

const Navbar = () => {

    const navigate = useNavigate();
    const location = useLocation();

    const pages = [
        { name: "Strona główna", link: "/" },
        { name: "Oferty produktów", link: "/market" },
        { name: "Posiadane produkty", link: "/fridge" }
    ]

    const changeSubSite = (link: string) => {
        return () => {
            navigate(link)
        }
    }

    const renderBackground = (path: string) => {
        return ((path.length === 1 && location.pathname.length === 1) ||
            (location.pathname.startsWith(path) && path.length === location.pathname.length))
    }

    return (
        <>
            <AppBar position='static' style={NavbarStyles}>
                <Container maxWidth={false}>
                    <Toolbar disableGutters>

                        <img src='http://www.clipartbest.com/cliparts/9iR/B7o/9iRB7oLie.png' style={{ height: '8vh' }} />
                        <img src='https://media.discordapp.net/attachments/966704303119171658/970037795995398144/unknown.png' style={{ height: '9.5vh' }} />

                        <Box sx={{ flexGrow: 1, display: 'flex' }}>
                            {pages.map((page) => (
                                <NavbarButton
                                    key={page.link}
                                    onClick={changeSubSite(page.link)}
                                    sx={{ my: 1, display: 'block', mx: 2 }}
                                    renderBackground={renderBackground(page.link)}
                                >
                                    {page.name}
                                </NavbarButton>
                            ))}
                        </Box>

                        <NavbarProfil/>
                    </Toolbar>
                </Container>
            </AppBar>

            <Outlet />
        </>
    );
}

export default Navbar;