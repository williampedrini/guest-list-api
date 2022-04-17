/* DDL: TABLE CREATION */
create table party
(
    id    bigint auto_increment primary key,
    place varchar(255) not null comment 'The place where it will happen.',
    name  varchar(255) not null comment 'The party name.',
    constraint name unique (name)
) comment 'Table containing all parties.';

create table party_table
(
    id              bigint auto_increment primary key,
    number          int    not null comment 'The table number.',
    number_of_seats int    not null comment 'The number of the table seats.',
    party_id        bigint not null comment 'The identifier of the party where this table will be placed.',
    constraint table_party_uk unique (number, party_id),
    constraint party_party_table_fk foreign key (party_id) references party (id) on delete cascade
) comment 'Table containing all the party''s tables.';

create table guest
(
    id                 bigint auto_increment primary key,
    name               varchar(255) not null comment 'The name of the person.',
    arrival_time       datetime     null comment 'The date and time that the guest arrived at the party.',
    leave_time         datetime     null comment 'The date and time that the guest left the party.',
    entourage_quantity int          null comment 'The number of people part of the guest''s entourage.',
    table_id           bigint       not null comment 'The identifier of the table where the guest will be placed.',
    constraint table_id unique (table_id),
    constraint guest_party_table_fk foreign key (table_id) references party_table (id) on delete cascade
) comment 'Table containing all the guests of a party.';

/* DML: SAMPLE DATA INSERTS */

/* PARTY SAMPLE DATA */
INSERT INTO `guest-list`.party (id, place, name)
VALUES (1, 'London Party House', 'GetGround 2021 end party');

/* PARTY TABLES SAMPLE DATA */
INSERT INTO `guest-list`.party_table (id, number, number_of_seats, party_id)
VALUES (1, 1, 10, 1);
INSERT INTO `guest-list`.party_table (id, number, number_of_seats, party_id)
VALUES (2, 2, 10, 1);
INSERT INTO `guest-list`.party_table (id, number, number_of_seats, party_id)
VALUES (3, 3, 10, 1);
INSERT INTO `guest-list`.party_table (id, number, number_of_seats, party_id)
VALUES (4, 4, 10, 1);
INSERT INTO `guest-list`.party_table (id, number, number_of_seats, party_id)
VALUES (5, 5, 10, 1);
INSERT INTO `guest-list`.party_table (id, number, number_of_seats, party_id)
VALUES (6, 6, 10, 1);
INSERT INTO `guest-list`.party_table (id, number, number_of_seats, party_id)
VALUES (7, 7, 10, 1);
INSERT INTO `guest-list`.party_table (id, number, number_of_seats, party_id)
VALUES (8, 8, 10, 1);
INSERT INTO `guest-list`.party_table (id, number, number_of_seats, party_id)
VALUES (9, 9, 10, 1);
INSERT INTO `guest-list`.party_table (id, number, number_of_seats, party_id)
VALUES (10, 10, 10, 1);
