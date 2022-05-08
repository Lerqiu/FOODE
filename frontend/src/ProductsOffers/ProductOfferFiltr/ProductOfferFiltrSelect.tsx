import { Button, MenuItem, Select, Typography } from "@mui/material";
import { useState } from "react";

const ProductOfferFiltrSelectStyle = {
    borderRadius: 4,
    backgroundColor: '#4E944F',
    border: '1px solid #ced4da',
    fontSize: 16,
    margin: '10px 26px 10px 12px',
    borderColor: '#83BD75',
    color: "white"
};

const ProductOfferFiltrSelect = () => {
    const [age, setAge] = useState('0');
    const handleChange = (event: { target: { value: string } }) => {
        setAge(event.target.value);
    };
    return (
        <Select
            labelId="demo-customized-select-label"
            id="demo-customized-select"
            value={age}
            onChange={handleChange}
            variant="filled"
            style={ProductOfferFiltrSelectStyle}
            // input={<Button/>}
        >
            <MenuItem value={0}><Typography variant="button" >Data dodania</Typography></MenuItem>
            <MenuItem value={10}><Typography variant="button" >Data ważności</Typography></MenuItem>
            <MenuItem value={20}><Typography variant="button" >Cena malejąco</Typography></MenuItem>
            <MenuItem value={30}><Typography variant="button" >Cena rosnąco</Typography></MenuItem>
        </Select >
    );
}

export default ProductOfferFiltrSelect;