import { RequestSignup } from '@api/io-model';
import { Component } from 'react';

import { sessionService } from '../../../model/user/session/Session.service';
import { FormField } from '../../base/form/FormField';
import { FormPageBackground } from '../../base/form/FormPageBackground';
import { FormPageContent } from '../../base/form/FormPageContent';
import { FormFieldPassword } from '../../base/form/FormPasswordBlock';
import { FormFieldText } from '../../base/form/FormTextBlock';

interface SignupPageState {
    errorMsg?: string;
}
export class SignupPage extends Component<unknown, SignupPageState> {
    constructor(props: unknown) {
        super(props);
        this.state = {};
        this.onFormSubmit = this.onFormSubmit.bind(this);
        this.errorMsg = this.errorMsg.bind(this);
    }
    private onFormSubmit(form: RequestSignup) {
        sessionService.signup(form).catch(this.errorMsg);
    }
    private errorMsg(errorMsg: string) {
        this.setState((state) => ({ ...state, errorMsg }));
    }
    override render(): React.ReactNode {
        const required = { required: true };
        const fields: FormField[] = [
            new FormFieldText('Username', [required]),
            new FormFieldPassword('Password', [required]),
        ];
        return (
            <FormPageBackground>
                <FormPageContent
                    title="Signup"
                    onSubmit={this.onFormSubmit}
                    fields={fields}
                    errorMsg={this.state.errorMsg}
                />
            </FormPageBackground>
        );
    }
}
