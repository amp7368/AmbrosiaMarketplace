import styled from '@emotion/styled';
import { divWrapperWith, StyledDiv100 } from 'elemental';
import { ReactNode } from 'react';
import { RegisterOptions } from 'react-hook-form';
import { FormField, FormFieldInput } from './FormField';

const FormTextErrorElement = divWrapperWith(
    styled.div(
        ({ theme }) => `
        color: ${theme.palette.error.contrastText};
    `
    )
);
const FormTextInput = styled(FormFieldInput)(() => ``);
const FormComponent = styled(StyledDiv100)(() => ``);

export class FormFieldText extends FormField {
    constructor(name: string, validations: RegisterOptions<any, any>[]) {
        super(name, validations);
        this.renderElement = this.renderElement.bind(this);
    }
    protected renderElement(registeredProps: any, error: any): JSX.Element {
        let errorElement: ReactNode;
        if (error !== undefined) {
            errorElement = (
                <FormTextErrorElement>
                    <p>* {registeredProps.error} *</p>
                </FormTextErrorElement>
            );
        }
        return (
            <FormComponent>
                <FormTextInput
                    placeholder={this.displayName}
                    {...registeredProps}
                />
                {errorElement}
            </FormComponent>
        );
    }
}
