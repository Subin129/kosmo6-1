
/* Drop Tables */

--DROP TABLE AUTH_SECURITY CASCADE CONSTRAINTS;
DROP TABLE memocomment CASCADE CONSTRAINTS;
DROP TABLE onememo CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE AUTH_SECURITY
(
	SECNO number NOT NULL,
	ENABLED number(1) DEFAULT 1,
	AUTHORITY varchar2(30),
	id varchar2(10),
	PRIMARY KEY (SECNO)
);


CREATE TABLE member
(
	id varchar2(10) NOT NULL,
	pwd varchar2(10) NOT NULL,
	name nvarchar2(10) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE memocomment
(
	cno number NOT NULL,
	linecomment varchar2(50) NOT NULL,
	cpostdate date DEFAULT SYSDATE,
	no number NOT NULL,
	id varchar2(10) NOT NULL,
	PRIMARY KEY (cno)
);


CREATE TABLE onememo
(
	no number NOT NULL,
	title nvarchar2(50) NOT NULL,
	content nvarchar2(2000) NOT NULL,
	postdate date DEFAULT SYSDATE,
	id varchar2(10) NOT NULL,
	PRIMARY KEY (no)
);



/* Create Foreign Keys */

ALTER TABLE AUTH_SECURITY
	ADD FOREIGN KEY (id)
	REFERENCES member (id)
;


ALTER TABLE memocomment
	ADD FOREIGN KEY (id)
	REFERENCES member (id)
;


ALTER TABLE onememo
	ADD FOREIGN KEY (id)
	REFERENCES member (id)
;


ALTER TABLE memocomment
	ADD FOREIGN KEY (no)
	REFERENCES onememo (no)
;



