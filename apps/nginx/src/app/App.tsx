import React from 'react';
import { DotDot } from './links/DotDot';
import { SubDomain } from './links/SubDomain';

function App() {
    return (
        <>
            <DotDot name=".." />
            <SubDomain name="express" newSubdomain="express" />
            <SubDomain name="client" newSubdomain="client" />
            <SubDomain name="nest" newSubdomain="nest" />
        </>
    );
}

export default App;
