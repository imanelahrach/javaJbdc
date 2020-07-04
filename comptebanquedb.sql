create table compte
(
    id          int auto_increment
        primary key,
    personne_id int default 0 not null,
    solde       double        null
);

create table personne
(
    cin    int auto_increment
        primary key,
    nom    varchar(50) null,
    prenom varchar(50) null
);

