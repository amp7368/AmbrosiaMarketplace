import { RequestSignup, UserCredentialsBase } from '@api/io-model';
import { Column } from 'typeorm';

export class UserCredentials implements UserCredentialsBase {
    assignProps(props: RequestSignup) {
        this.email = props.email ?? null;
        this.username = props.username;
        this.password = props.password;
    }

    @Column({ nullable: true })
    email: string;

    @Column()
    username: string;

    @Column()
    password: string;
}
