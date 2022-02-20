import { Box } from '@mui/material';
import { Subject } from 'rxjs';

import { AppTabContent } from '../../base/tab/AppTabContent';
import { AppTabSingleProps, AppTabsProps } from '../../base/tab/AppTabProps';
import { AppTabSwitcher } from '../../base/tab/AppTabSwitcher';
import { PageWrapper } from '../PageWrapper';
import { LoginPage } from './LoginPage';
import { SignupPage } from './SignupPage';

enum AuthPageTab {
    LOGIN,
    SIGNUP,
}

const tabs: AppTabSingleProps<AuthPageTab>[] = [
    {
        tabKey: AuthPageTab.LOGIN,
        tabElement: 'login',
        renderPage: () => <LoginPage />,
    },
    {
        tabKey: AuthPageTab.SIGNUP,
        tabElement: 'signup',
        renderPage: () => <SignupPage />,
    },
];
const props: AppTabsProps<AuthPageTab> = {
    tabs: tabs,
    onTabSwitch: new Subject<AuthPageTab>(),
    defaultPage: AuthPageTab.LOGIN,
};
function AuthPage() {
    return (
        <Box>
            <AppTabSwitcher {...props} />;
            <AppTabContent {...props} />
        </Box>
    );
}
class AuthPageWrapper extends PageWrapper {
    public renderMainPage = () => {
        return <AuthPage />;
    };
}
export default AuthPageWrapper;
