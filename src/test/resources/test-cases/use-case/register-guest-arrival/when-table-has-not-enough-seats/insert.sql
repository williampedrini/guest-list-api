insert into party (id, place, name)
values (1, 'London Party House', 'GetGround 2021 end party');

insert into party_table (id, number, number_of_seats, party_id)
values (1,
        1,
        5,
        (select p.id from party p where p.name = 'GetGround 2021 end party'));

insert into guest (id, name, entourage_quantity, table_id)
values (1,
        'Wanda Hum',
        9,
            (select pt.id
            from party p
            inner join party_table pt on pt.party_id = p.id
            where p.name = 'GetGround 2021 end party'
            and pt.number = 1
        ));