insert into party (id, place, name)
values (1, 'London Party House', 'GetGround 2021 end party');

insert into party_table (id, number, number_of_seats, party_id)
values (1,
        1,
        10,
        (select p.id from party p where p.name = 'GetGround 2021 end party'));

insert into guest (id, name, entourage_quantity, table_id)
values (1,
        'Jake Fuller',
        10,
        (select pt.id from party_table pt where pt.number = '1'));