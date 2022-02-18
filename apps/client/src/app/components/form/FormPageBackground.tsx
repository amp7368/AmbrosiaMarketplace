import styled from '@emotion/styled';
import { divWrapperWith } from 'elemental';

export const FormPageBackground = divWrapperWith(
    styled('div')(
        (props) => `
        display: flex;
        justify-content: center;
        align-items: center;
        width: 600px;
        height: 100%;
        margin: auto;
    `
    ),
    styled('div')(
        (props) => `
        display: flex;
        justify-content: center;
        align-items: center;
        height: 500px;
        width: 100%;
        background-color: ${props.theme.palette.primary.dark};
    `
    )
);
