import { RegisterOptions } from 'react-hook-form';
import styled from '@emotion/styled';
import { divWrapperWith } from 'elemental';
import { FormField, FormFieldComponent, FormFieldInput } from './FormField';

const FormPasswordErrorElement = divWrapperWith(
    styled.div(
        ({ theme }) => `
        color: ${theme.palette.error.contrastText};
    `
    )
);
const FormPasswordInput = styled(FormFieldInput)``;
const FormPasswordComponent = styled(FormFieldComponent)``;

export class FormFieldPassword extends FormField {
    constructor(name: string, validations: RegisterOptions<any, any>[]) {
        super(name, validations);
        this.renderElement = this.renderElement.bind(this);
    }
    protected renderElement(
        registeredProps: { error?: unknown },
        error: any
    ): JSX.Element {
        let errorElement: JSX.Element | null;
        if (error === undefined) {
            errorElement = null;
        } else {
            errorElement = (
                <FormPasswordErrorElement>
                    <p>* {registeredProps.error} *</p>
                </FormPasswordErrorElement>
            );
        }
        return (
            <FormPasswordComponent>
                <FormPasswordInput
                    type="password"
                    placeholder={this.displayName}
                    {...registeredProps}
                />
                {errorElement}
            </FormPasswordComponent>
        );
    }
}
