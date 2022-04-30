import React from 'react'
import { Outlet } from 'react-router-dom';

function Navbar() {
    return (
        <>
            <div style={{ height: '100px', width: '100%', backgroundColor: 'red' } as React.CSSProperties}>
                <h1>Raw navbar</h1>
            </div>
            <Outlet />
        </>
    );
}

export default Navbar;