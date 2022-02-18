import { Component } from 'react';
import { sessionService } from '../../../model/user/session/Session.service';
import { FormPageBackground } from '../../form/FormPageBackground';
import { FormPageContent } from '../../form/FormPageContent';
import { FormFieldPassword } from '../../form/FormPasswordBlock';
import { FormFieldText } from '../../form/FormTextBlock';
import { PageWrapper } from '../PageWrapper';

interface LoginPageFormData {
    username: string;
    password: string;
}
interface LoginPageState {
    errorMsg: string;
}

class LoginPageContent extends Component<unknown, LoginPageState> {
    constructor(props: unknown) {
        super(props);
        this.state = { errorMsg: '' };
        this.onFormSubmit = this.onFormSubmit.bind(this);
        this.errorMsg = this.errorMsg.bind(this);
    }
    private onFormSubmit(form: LoginPageFormData) {
        sessionService.login(form).catch(this.errorMsg);
    }
    private errorMsg(errorMsg: any) {
        console.log('oof' + errorMsg);
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
class LoginPageWrapper extends PageWrapper {
    public renderMainPage = () => {
        return <LoginPageContent />;
    };
}
export default LoginPageWrapper;
