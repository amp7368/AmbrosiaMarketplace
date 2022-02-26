import styled from '@emotion/styled';
import { ObserveableToElement, StyledDiv100 } from 'elemental';
import { sessionQuery } from '../../../model/user/session/Session.query';
import { PageWrapper } from '../PageWrapper';

const StyledMainPageRoot = styled(StyledDiv100)`
    display: inline-block;
`;
const ProfileInventory = styled.div`
    display: inline-block;
    height: 50%;
    width: 100%;
`;
const ProfileMarketSlots = styled.div`
    display: inline-block;
    height: 50%;
    width: 100%;
`;

const SessionToken = () => {
    return ObserveableToElement({
        original: sessionQuery.sessionToken,
        mappingFn: (sessionToken) => {
            const display = sessionToken ?? 'Session token is invalid';
            return <h1>{display}</h1>;
        },
    });
};
class ProfilePage extends PageWrapper {
    public renderMainPage = () => {
        return (
            <StyledMainPageRoot>
                <ProfileMarketSlots></ProfileMarketSlots>
                <ProfileInventory>
                    <h1>Heyo</h1>
                    <SessionToken />
                </ProfileInventory>
            </StyledMainPageRoot>
        );
    };
}
export default ProfilePage;
