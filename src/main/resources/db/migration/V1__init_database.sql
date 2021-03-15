drop table if exists bookings;
drop table if exists guests;
drop table if exists facilities;
drop table if exists payments;

create table bookings(
    id int primary key AUTO_INCREMENT,
    guest_id  int not null,
    payment_id int,
    facility_id int not null,
    count_of_guests int not null,
    start_of_booking date not null,
    end_of_booking date not null,
    description varchar(100)
);

create table guests(
    id int primary key auto_increment,
    name varchar(100) not null,
    email varchar(100) not null,
    phone_number varchar(100),
    additional_information varchar(100)
    );

create table facilities(
    id int primary key auto_increment,
    name varchar(100) not null,
    basic_rent_amount int not null
);

create table payments(
    id int primary key auto_increment,
    code varchar(100) not null,
    cost_per_night int not null,
    discount int,
    advance_size int not null,
    is_advance_paid bit,
    deadline_for_payment date not null,
    booking_id int
);

alter table payments
add foreign key (booking_id) references bookings (id);

alter table bookings
    add foreign key (guest_id) references guests (id);
alter table bookings
    add foreign key (payment_id) references payments (id);
alter table bookings
    add foreign key (facility_id) references facilities (id);
