import { AppBar, Box, Container, Toolbar } from '@mui/material';
import { Outlet, useLocation, useNavigate } from 'react-router-dom';
import NavbarButton from './Button/NavbarButton';
import NavbarStyles from './Navbar.styles'
import NavbarProfile from './Profile/NavbarProfile';


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


    const doRenderBackground = (path: string) => {
        const pathname = location.pathname

        // Match to: /
        const isMain: boolean = (path.length === 1 && pathname.length === 1)

        //Match to: /xyz
        const isSimpleSubSite: boolean = (pathname.startsWith(path) && path.length === pathname.length)

        //Match to: /xyz/qwe/...
        const isNestedSubSite: boolean = (pathname.startsWith(path) && path.length < pathname.length && pathname[path.length] === '/')
        return (isMain || isSimpleSubSite || isNestedSubSite)
    }

    return (
        <Box sx={{ minHeight: "100vh", display: "flex", flexFlow: "column" }}>
            <AppBar position='static' style={NavbarStyles}>
                <Container maxWidth={false}>
                    <Toolbar disableGutters>

                        <img src='https://media.discordapp.net/attachments/966704303119171658/970037795995398144/unknown.png' style={{ height: '9.5vh' }} />

                        <Box sx={{ flexGrow: 1, display: 'flex' }}>
                            {pages.map((page) => (
                                <NavbarButton
                                    key={page.link}
                                    onClick={changeSubSite(page.link)}
                                    sx={{ my: 1, display: 'block', mx: 2 }}
                                    renderBackground={doRenderBackground(page.link)}
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