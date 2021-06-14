create table author
(
   id integer PRIMARY KEY AUTO_INCREMENT,
   email varchar(50) NOT NULL,
   first_name varchar(50) NOT NULL,
   last_name varchar(60) NOT NULL
);


create table publication
(
   id integer PRIMARY KEY AUTO_INCREMENT,
   isbn varchar(50) NOT NULL,
   title varchar(50) NOT NULL,
   description varchar(150), 
   issue_date DATE, 
   d_type char NOT NULL, 
   p_type char NOT NULL
);


create table publication_author
(
   author_id int, 
   publication_id int, 
   FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (publication_id) REFERENCES publication(id) ON DELETE CASCADE ON UPDATE CASCADE
);
