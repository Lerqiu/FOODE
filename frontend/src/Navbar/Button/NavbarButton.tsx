import { Button, ButtonProps } from '@mui/material';
import { NavbarButton_Background, NavbarButton_Raw } from './NavbarButton.styles'

type NavbarButtonType = ButtonProps & { renderBackground: boolean }

const NavbarButton = (props: NavbarButtonType) => {
    const buttonProps = { ...props } as any
    delete buttonProps.renderBackground

    const style = props.renderBackground ? NavbarButton_Background : NavbarButton_Raw;

    return <Button style={style} {...buttonProps} />;
}

export default NavbarButton;