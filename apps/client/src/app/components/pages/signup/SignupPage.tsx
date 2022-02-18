import { RequestSignup } from '@api/io-model';
import { Component } from 'react';
import { sessionService } from '../../../model/user/session/Session.service';
import { FormPageBackground } from '../../form/FormPageBackground';
import { FormPageContent } from '../../form/FormPageContent';
import { FormFieldText } from '../../form/FormTextBlock';
import { PageWrapper } from '../PageWrapper';

interface SignupContentState {
    errorMsg: string;
}
class SignupContent extends Component<unknown, SignupContentState> {
    private onSignup(form: RequestSignup) {
        sessionService.signup(form).catch(this.setExtraMessage);
    }
    setExtraMessage(errorMsg: string) {
        this.setState((state) => ({ ...state, errorMsg: errorMsg }));
    }
    override render() {
        const required = { required: true };
        const fields = [
            new FormFieldText('Username', [required]),
            new FormFieldText('Password', [required]),
        ];
        return (
            <FormPageBackground>
                <FormPageContent
                    title="Signup"
                    onSubmit={this.onSignup}
                    fields={fields}
                    errorMsg={this.state.errorMsg}
                />
            </FormPageBackground>
        );
    }
}
class SignupPage extends PageWrapper {
    override renderMainPage = () => {
        return <SignupContent />;
    };
}
export default SignupPage;
