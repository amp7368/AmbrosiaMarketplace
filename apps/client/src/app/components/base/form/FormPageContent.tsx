import styled from '@emotion/styled';
import { Typography } from '@mui/material';
import { divWrapperWith } from 'elemental';
import { useForm } from 'react-hook-form';
import { FormField } from './FormField';

export interface FormPageContentProps {
    onSubmit: (data: any) => void;
    title: string;
    fields: FormField[];
    errorMsg?: string;
}
const formFieldHeight = 100;
const StyledFormField = styled.div(
    () => `
    width: 75%;
    height: 40px;
    display: block;
    margin-left: auto;
    margin-right: auto;
    height: ${formFieldHeight}px;
    display: block;
`
);
const TopFormFieldContainer = styled(StyledFormField)`
    height: ${formFieldHeight / 2}px;
`;
const TopFormFieldText = styled.div(
    ({ theme }) => `
    color: ${theme.palette.primary.contrastText};
    font-size: x-large;
    font-weight: bold;
    line-height: 0;
    padding-top: ${formFieldHeight / 4}px;
    display: inline-block;
    margin-left: 0;
`
);
const TopFormField = divWrapperWith(TopFormFieldContainer, TopFormFieldText);
const StyledForm = styled.form(
    () => `
    width: 100%;
    height: 100%;
`
);
const StyledErrorMessage = styled(Typography)(
    ({ theme }) => `
    color: ${theme.palette.error.main}
`
);
const StyledSubmit = styled.input(
    ({ theme }) => `
    cursor: pointer;
    outline: none;
    border-style: solid;
    color: ${theme.palette.secondary.contrastText};
    border-color: ${theme.palette.primary.main};
    background-color: ${theme.palette.secondary.main};
    font-size: large;
    font-weight: bold;
    border-width: 3px;
    height: 50%;
    width: 50%;
    display: block;
    margin-left: auto;
    margin-right: auto;
    border-radius: 20px;
`
);
export function FormPageContent(props: FormPageContentProps) {
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm();
    const length = props.fields.length;
    const fields = props.fields.map((field: FormField, i: number) => (
        <StyledFormField key={i}>
            {field.getElement(register, errors)}
        </StyledFormField>
    ));
    let errorElement;
    if (props.errorMsg) {
        errorElement = (
            <StyledErrorMessage>{props.errorMsg}</StyledErrorMessage>
        );
    } else {
        errorElement = null;
    }

    return (
        <StyledForm onSubmit={handleSubmit(props.onSubmit)}>
            <TopFormField count={length}>{props.title}</TopFormField>
            {fields}
            <StyledFormField>
                <StyledSubmit type="submit" />
            </StyledFormField>
            {errorElement}
        </StyledForm>
    );
}
