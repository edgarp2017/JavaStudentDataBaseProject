create table classes
(
    classCode int(255)    not null,
    courseID  varchar(255) not null,
    studentID int(255) not null,
    year      int(4)      null,
    semester  varchar(10) null,
    gpa       varchar(1)  null,
    primary key (courseID, studentID, gpa)
);

create table courses
(
    ID    varchar(255)     not null,
    courseTitle varchar(255) null,
    department  varchar(200) null,
    primary key (ID)
);

create table students
(
    ID int(255) not null,
    firstName varchar(20)  null,
    lastName  varchar(50)  null,
    sex       varchar(1)   null,
    primary key (ID)
);

alter table classes
    add constraint cID
        foreign key(courseID) references courses(ID)
            ON DELETE CASCADE;

alter table classes
    add constraint sID
        foreign key(studentID) references students(ID)
            ON DELETE CASCADE;

INSERT INTO `Courses` VALUES ('CSC597','Decentralized bifurcated application','Comp Sci'),
                ('CSC211','Software Design Lab','Comp Sci'),
                ('MAT201','Calculus I','Math'),
                ('MAT978','Total radical functionalities','Math');

INSERT INTO `Students` VALUES (20205556,'Everette','Raynor','F'),
                (20281868,'Rosalinda','Stanton','F'),
                (20539438,'Gerson','Schuster','F'),
                (20606424,'Maryjane','Kunze','F'),
                (20631347,'Sylvester','O', 'M'),
                (20736321,'Issac','Kemmer','M'),
                (20916199,'Rickie','Lesch','F'),
                (20956574,'Margarett','OConner','M'),
                (22027844,'Lucie','Willms','M'),
                (22122849,'Lula','Grant','M'),
                (22441204,'Justyn','Schulist','F'),
                (22534969,'Dedric','Durgan','F'),
                (22840796,'Jewell','Fadel','M'),
                (23068353,'Erich','Jast','F'),
                (23105800,'Ophelia','Kunze','M'),
                (23297894,'Carlo','Fahey','M'),
                (39715621,'Sarai','Stokes','M');

INSERT INTO `Classes` VALUES (50023,'CSC211',23068353,'2019','Fall','A'),
                (50023,'CSC211',20956574,2019,'Fall','B'),
                (50023,'CSC211',23297894,2019,'Fall','B'),
                (50023,'CSC211',20606424,2019,'Fall','C'),
                (50023,'CSC211',20631347,2019,'Fall','C'),
                (50023,'CSC211',22122849,2019,'Fall','W'),
                (50023,'MAT978',20606424,2019,'Fall','A'),
                (50023,'MAT201',20281868,2019,'Fall','A'),
                (50023,'MAT201',23068353,2019,'Fall','A'),
                (50023,'MAT201',22840796,2019,'Fall','F'),
                (50023,'MAT201',20606424,2019,'Fall','B'),
                (50023,'CSC211',22027844,2019,'Fall','F');