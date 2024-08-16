
CREATE USER 'Sensor'@localhost IDENTIFIED BY 'Passord';
CREATE SCHEMA universityDB;
FLUSH PRIVILEGES;
CREATE SCHEMA eventDB;
FLUSH PRIVILEGES;
USE universityDB;
GRANT ALL ON universityDB TO 'Sensor'@'localhost';
USE eventDB;
GRANT ALL ON eventDB TO 'Sensor'@'localhost';


USE universityDB;
CREATE TABLE staff
(
    staffID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    staffName char(200)
);

USE universityDB;
CREATE TABLE studyProgram
(
    studyProgramID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    studyProgram char(100),
    programResponsible int,
    FOREIGN KEY(programResponsible) REFERENCES staff(staffID)
);
USE universityDB;
CREATE TABLE student
(
    studentID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    studentName char(200),
    user char(200) NOT NULL UNIQUE,
    studyProgram int,
    FOREIGN KEY (studyProgram) REFERENCES studyProgram(studyProgramID)
);

USE eventDB;
CREATE TABLE student_guest (
                               guestID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                               guestName char(200),
                               studentID int,
                               FOREIGN KEY (studentID) REFERENCES universityDB.student(studentID)
);

USE eventDB;
CREATE TABLE attendants (
                            attendantID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                            studentID int,
                            studentName char(200) references universityDB.student(studentName),
                            guestID int,
                            guestName char(200) references student_guest(guestName),
                            staffID int,
                            staffName char(200) references universityDB.staff(staffName),
                            FOREIGN KEY (studentID) REFERENCES universityDB.student(studentID),
                            FOREIGN KEY (guestID) REFERENCES student_guest(guestID),
                            FOREIGN KEY (staffID) REFERENCES universityDB.staff(staffID)
);


USE universityDB;
INSERT INTO staff (staffID, staffName) VALUES
                                           (1, 'Per Persen'),
                                           (2, 'Tomas Tomasen'),
                                           (3, 'Bengt Bengtsen'),
                                           (4, 'Marcus Marcusen');

USE universityDB;
INSERT INTO studyProgram (studyProgramID, studyProgram, programResponsible) VALUES
                                           (1, 'CyberSec', 3),
                                           (2, 'UxDesign', 1),
                                           (3, 'Programmering', 4),
                                           (4, 'Frontend', 2);

USE universityDB;
INSERT INTO student (studentID, studentName,user, studyProgram) VALUES
                                           (1, 'Lisa Lisesen', 'LiseLiss', 1),
                                           (2, 'Mona Monesen', 'GamerMon', 2),
                                           (3, 'Alice Alicesen', 'CoolCat', 3),
                                           (4, 'Mina Minesen', 'PassordPassord', 4);