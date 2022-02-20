import { RegisterOptions, UseFormRegister } from 'react-hook-form';
import styled from '@emotion/styled';

export const FormFieldComponent = styled.div``;
const border_size = `3px`;
export const FormFieldInput = styled.input(
    (props) => `
    display: block;
    border-style: solid;
    background-color: ${props.theme.palette.background};
    font-size: larger;
    font-weight: bold;
    color: ${props.theme.palette.primary.contrastText};
    text-indent: 1ch;
    justify-content: left;

    caret-color: ${props.theme.palette.primary.contrastText};
    border-color: ${props.theme.palette.primary.contrastText};
    border-radius: 10px;
    border-width: ${border_size};
    margin-top: auto;
    margin-bottom: 0;
    width: calc(100% - 2 * ${border_size});
    height: 50px;
    :hover {
        border-style: solid;
        border-color: ${props.theme.palette.primary.contrastText};
        outline: none;
    }
    :focus {
        border-style: solid;
        border-color: ${props.theme.palette.primary.contrastText};
        outline: none;
    }
    :active {
        border-style: solid;
        border-color: ${props.theme.palette.primary.contrastText};
        outline: none;
    }
    ::placeholder {
        color: ${props.theme.palette.primary.contrastText};
    }
`
);

export abstract class FormField {
    displayName: string;
    normalizedName: string;
    validations: RegisterOptions<any, any>[];
    constructor(name: string, validations: RegisterOptions<any, any>[]) {
        this.displayName = name;
        this.normalizedName = name.toLowerCase();
        this.validations = validations;
        this.getElement = this.getElement.bind(this);
    }
    getElement(register: UseFormRegister<any>, errors: { [x: string]: any }) {
        return this.renderElement(
            register(this.normalizedName, ...this.validations),
            errors[this.normalizedName]
        );
    }
    protected abstract renderElement(props: any, error: any): JSX.Element;
}
