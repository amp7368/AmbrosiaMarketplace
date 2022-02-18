import styled from '@emotion/styled';
import { observeableToElement, StyledDiv100 } from 'elemental';
import { isFalsey } from '../../../../../../../libs/misc-for-now/src/lib/falsey/falseyTruthy';
import { sessionQuery } from '../../../model/user/session/Session.query';
import { ItemDisplayGrid } from '../../item/ItemDisplayGrid';
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
    return observeableToElement({
        original: sessionQuery.sessionToken,
        mappingFn: (sessionToken) => {
            let display: string;
            if (isFalsey(sessionToken)) display = 'Session token is invalid';
            else display = sessionToken as string;
            return <h1>{display}</h1>;
        },
    });
};
class ProfilePage extends PageWrapper {
    public renderMainPage = () => {
        return (
            <StyledMainPageRoot>
                <ProfileMarketSlots>
                    <ItemDisplayGrid items={[]} />
                </ProfileMarketSlots>
                <ProfileInventory>
                    <h1>Heyo</h1>
                    <SessionToken />
                </ProfileInventory>
            </StyledMainPageRoot>
        );
    };
}
export default ProfilePage;
