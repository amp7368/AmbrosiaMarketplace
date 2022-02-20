import { RequestLogin } from '@api/io-model';
import { Component } from 'react';

import { sessionService } from '../../../model/user/session/Session.service';
import { FormPageBackground } from '../../base/form/FormPageBackground';
import { FormPageContent } from '../../base/form/FormPageContent';
import { FormFieldPassword } from '../../base/form/FormPasswordBlock';
import { FormFieldText } from '../../base/form/FormTextBlock';

interface LoginPageState {
    errorMsg?: string;
}

export class LoginPage extends Component<unknown, LoginPageState> {
    constructor(props: unknown) {
        super(props);
        this.state = {};
        this.onFormSubmit = this.onFormSubmit.bind(this);
        this.errorMsg = this.errorMsg.bind(this);
    }
    private onFormSubmit(form: RequestLogin) {
        sessionService.login(form).catch(this.errorMsg);
    }
    private errorMsg(errorMsg: string) {
        this.setState((state) => ({ ...state, errorMsg }));
    }
    override render(): React.ReactNode {
        const required = { required: true };
        const fields = [
            new FormFieldText('Username', [required]),
            new FormFieldPassword('Password', [required]),
        ];
        return (
            <FormPageBackground>
                <FormPageContent
                    title="Login"
                    onSubmit={this.onFormSubmit}
                    fields={fields}
                    errorMsg={this.state.errorMsg}
                />
            </FormPageBackground>
        );
    }
}
