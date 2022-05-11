import { AccountCircle } from "@mui/icons-material";
import { Button, Icon } from "@mui/material";
import ProfilButton from './NavbarProfile.styles'

const NavbarProfile = () => {
    return (
        <Button variant="text" style={ProfilButton}>Profil
            <Icon sx={{ ml: 1 }}>
                <AccountCircle />
            </Icon>
        </Button>
    );
}
export default NavbarProfile;