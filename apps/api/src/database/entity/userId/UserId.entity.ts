import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class UserId {
    @PrimaryGeneratedColumn()
    uuid: string;

    @Column({ nullable: true, unique: true })
    ambrosiaId?: string;
    @Column({ nullable: true })
    ambrosiaUsername?: string;

    @Column({ nullable: true, unique: true })
    minecraftId?: string;
    @Column({ nullable: true })
    minecraftUsername?: string;

    @Column({ nullable: true, unique: true })
    discordId?: number;
    @Column({ nullable: true })
    discordUsername?: string;
}
