use master
go

if exists(select * from sys.databases where name='TakeawayDB')
  drop database TakeawayDB
go

create database TakeawayDB
go

use TakeawayDB
go

--»áÔ±ÀàĞÍ±í
create table VipType
(
  vtid int primary key identity(1,1),--Ö÷¼ü ×Ô¶¯ ±àºÅ
  vtname varchar(16) unique not null,--Î¨Ò» ·Ç¿Õ Ãû³Æ
  vtdiscount smallmoney default(1) --ÕÛ¿Û
)
go
insert into VipType( vtname, vtdiscount) values
( 'v5', 1)
,( 'v2', 0.98)
,( 'v3', 0.95)
,( 'v4', 0.90)
select * from vipType
go

--»áÔ±±í
create table Vip
(
  vid int primary key identity(1,1),--Ö÷¼ü ×Ô¶¯	±àºÅ
  vaccountNumber char(13) UNIQUE not null,--ÕËºÅ ¹Ì¶¨ Î¨Ò»
  vpassword varchar(16) not null,--µÇÂ¼ÃÜÂë
  vvtid int foreign key references vipType(vtid) not null,--»áÔ±ÀàĞÍ
  vname nvarchar(16),--ĞÕÃû
  vsex nvarchar(1) check(vsex='ÄĞ'or vsex='Å®') default('ÄĞ'),--ĞÔ±ğ 1ÄĞ | 0Å®
  vheadPortrait nvarchar(225),--Í·Ïñ
  vbirthday date,--ÉúÈÕ
  vregion nvarchar(225), --µØÇø
  vemail varchar(100),--µç×ÓÓÊÏä
  vflavor nvarchar(64),--¿ÚÎ¶Ï²ºÃ
  vpersonality nvarchar(64),--¸öĞÔ±êÇ©
  vintegral int default(0),--»ı·Ö
  vbalance money default(0),--Óà¶î
  vpaymentPassword char(6)--Ö§¸¶ÃÜÂë ¹Ì¶¨
)
go

--select * from orders;
--select oNumber,oorderDate,omoney,oinvoice,ovid,osid,oremark,ostatus from Orders join Vip on Orders.ovid=Vip.vid where oNumber=?

select cid,mname as coNumber,cquantity from Consumption left join
 (select mid,mname from Menu) as m on m.mid=Consumption.cmid where coNumber='0724153609665332'


insert into Vip(vaccountNumber,vpassword,vvtid,vbirthday) values('15070237082','666666',1,'1997-10-20')
insert into Vip(vaccountNumber,vpassword,vvtid,vbirthday) values('13926901506','123456',1,'1997-10-20')
select * from Vip
go

--»áÔ±µØÖ·±í
create table Locations
(
  lid  int primary key identity(1,1),--Ö÷¼ü ×Ô¶¯ ±àºÅ
  lvid int foreign key references Vip(vid) not null,--»áÔ±
  lname nvarchar(16) not null,--ÁªÏµÈËÃû³Æ
  lsex varchar(2) check(lsex='ÄĞ'or lsex='Å®') default('ÄĞ'),--ĞÔ±ğ 1ÄĞ | 0Å®,--ĞÔ±ğ
  lphone char(13) not null,--ÁªÏµµç»° ¹Ì¶¨
  laddress nvarchar(225),--ÏêÏ¸µØÖ·
  lstatus bit default(0) -- 1Ä¬ÈÏ
)
go
insert into Locations(lvid,lname,lsex,lphone,laddress,lstatus) values(1,'Å®Ê¿','Å®','15070237084','ÄÏ·½IT',0)
--delete Locations where lvid=4
select * from Locations 
select * from Locations where lvid=1 and lstatus=1
go




--²Ëµ¥ÀàĞÍ
create table MenuType
(
  mtid int primary key identity(1,1),--Ö÷¼ü ×Ô¶¯	±àºÅ
  mtname nvarchar(16) not null,--Ãû³Æ
  mtParentClass int default(0),--¸¸Àà
)
go
insert into menuType(mtname,mtParentClass) values
('³¬Öµ',0)
,('ÓªÑøÔç²Í',0)
,('·á¸»Ö÷Ê³',0)
,('ÇáËÉÏÂÎç²è',0)
,('Ì×²ÍÓªÑøÅä',2)
,('ÊÀ½ç±­ÀñºĞ',3)
,('Ç××ÓÌ×²Í', 3)
,('½´Ïã»¨ëî·¹', 3)
,('ÏãÖ­ÅÅ¹Ç·¹', 3)
,('Íü²»ÁËÏãÖ­ÅÅ¹Ç·¹', 3)
,('ĞÂÆ·-ÆßµÀ¹¦·òÉÕÈâÍè', 3)
,('ºÀ»ªµ¥ÈËÌ×²Í', 3)
,('¶¦¼¶·ÊÅ£·¹', 3)
,('Åä²Ë/Ã×·¹', 3)
,('ÕôÌÀ', 3)
,('ÓãÏãÇÑ×Ó·¹', 3)
,('ÏÊÀ±ÅÅ¹Ç·¹', 3)
,('Ëá²ËÂ±Èâ·¹', 3)
,('¶¬£¨Ïã£©¹½¼¦ÍÈÈâ·¹', 3)
,('ÅÅ¹ÇÆ´¼¦ÍÈÈâ·¹', 3)
,('³¬ÖµË«ÈË²Í', 3)
,('Ì×²Í', 4)
,('Ã×·Û¡¢Ã×Ïß', 4)
,('Ğ¡³Ô', 4)
,('ÒûÆ·', 4)
select * from menuType where mtParentClass = 0
go


--²Ëµ¥
create table Menu
(
  mid  int primary key identity(1,1),--Ö÷¼ü ×Ô¶¯ ±àºÅ
  mmtid int foreign key references MenuType(mtid) not null,--²Ëµ¥ÀàĞÍ
  mname nvarchar(16) not null,--²ËÃû
  mprice smallmoney not null,--Ô­¼Û
  mdiscount smallmoney default(1), --ÕÛ¿Û
  mdate datetime default(getdate()),--Ìí¼ÓÊ±¼ä
  mstatus bit default(0)--×´Ì¬ 0ÉÏ¼Ü | 1ÏÂ¼Ü
)
go
insert into Menu(mmtid, mname,mprice,mdate) values
(1,'µ¥ÈËÄÜÁ¿²Í¼ÓÏíÊÀ½ç±­Àñ°üGFS', 161.6,getdate())
,(1,'Ë«ÈËÄÜÁ¿²Í¼ÓÏíÊÀ½ç±­Àñ°üGFS', 194.4,getdate())
,(1,'¾­µäÅÅ¹ÇË«ÈË²ÍGFS', 72.5,getdate())
,(1,'Â±Èâ¶¬¹½¼¦Ë«ÈË²ÍGFS', 66.5, getdate())
,(1,'½´Ïã»¨ëîÎÚ¼¦ÌÀÌ×²ÍGFS', 26.4, getdate())
,(1,'½´Ïã»¨ëîºÀ»ª²ÍGFS', 40,getdate())
,(1,'½´Ïã»¨ëîÂú×ã²ÍGFS', 36,getdate())
,(5,'ÏÊÈâôÕÊİÈâ·ÛGFS', 16, getdate())
,(6,'µ¥ÈËÄÜÁ¿²Í¼ÓÏíÊÀ½ç±­Àñ°üGFS',161.6, getdate())
,(6,'Ë«ÈËÄÜÁ¿²Í¼ÓÏíÊÀ½ç±­Àñ°üGFS', 194.4, getdate())
,(7,'Ë«Æ´Â±ÈâÀÖÏíÇ××Ó²ÍGFS', 84, getdate())
,(8,'½´Ïã»¨ëîÑ©ÀæÂ¶Ì×²ÍGFS',28, getdate())
,(8,'½´Ïã»¨ëîŞ²ÈÊË¬Ì×²ÍGFS',28, getdate())
,(8,'½´Ïã»¨ëîË«ÈËÌ×²ÍGFS',60, getdate())
,(8,'½´Ïã»¨ëîÅÅ¹ÇË«ÈË²ÍGFS', 70.55, getdate())
,(8,'½´Ïã»¨ëîÎÚ¼¦ÌÀÌ×²ÍGFS', 26.4, getdate())
,(8,'½´Ïã»¨ëî·¹GFS',18, getdate())
,(8,'½´Ïã»¨ëîºÀ»ª²ÍGFS', 40, getdate())
,(8,'½´Ïã»¨ëîÂú×ã²ÍGFS',36, getdate())
,(8,'½´Ïã»¨ëîÌÀÌ×²ÍGFS', 28, getdate())
,(9,'ÅÅ¹ÇÌÀÌ×²Í',31.5 , getdate())
,(9,'ÅÅ¹ÇÌ×·¹', 21.5, getdate())
,(9,'ÏãÖ­ÅÅ¹Ç·¹',19.5, getdate())
,(9,'ÅÅ¹ÇÒûÁÏÌ×²Í',29.5, getdate())
,(9,'ÏãÖ­ÅÅ¹Ç²Ë',15.5, getdate())
,(10,'ËáÀ±µ°¸ÉÅÅ¹ÇÌÀÌ×²ÍGFS',39.5, getdate())
,(11,'ÈâÍèÌÀÌ×£¨Éı¼¶Ÿh³á£©',33, getdate())
,(11,'ÈâÍèÌÀÌ×£¨Éı¼¶Õôµ°£©',31, getdate())
,(11,'ÆßµÀ¹¦·òÉÕÈâÍèÒûÁÏÌ×²Í',30, getdate())
,(11,'ÆßµÀ¹¦·òÉÕÈâÍèº¬ÌÀÌ×²Í',30, getdate())
,(11,'ÆßµÀ¹¦·òÉÕÈâÍè·¹', 20, getdate())
,(11,'ÆßµÀ¹¦·òÉÕÈâÍè²Ë', 17, getdate())
,(12,'À±¹ÇºÀ»ªµ¥ÈËÌÀÌ×', 40.5, getdate())
,(12,'Ëá²ËÂ±ÈâºÀ»ªµ¥ÈËÌÀÌ×',38.5, getdate())
,(12,'ÏãÖ­ÅÅ¹ÇÂú×ã²Í',36.5, getdate())
,(12,'ÏÊÀ±ÅÅ¹ÇÂú×ã²Í',36.5, getdate())
,(12,'¶¬¹½¼¦ÍÈÈâºÀ»ªµ¥ÈËÌÀÌ×', 38.5, getdate())
,(12,'ÏãÖ­ÅÅ¹ÇºÀ»ªµ¥ÈËÌÀÌ×',40.5, getdate())
,(13,'¶¦¼¶·ÊÅ£²Ë', 17.5, getdate())
,(13,'¶¦¼¶·ÊÅ£·¹', 20.5, getdate())
,(13,'·ÊÅ£Ì×·¹',23, getdate())
,(13,'·ÊÅ£ÒûÁÏÌ×',30, getdate())
,(13,'¶¦¼¶·ÊÅ£·¹ÌÀÌ×²Í',32, getdate())
,(14,'ÍâÆÅ²Ë',4, getdate())
,(14,'İ®¿ª¶ş¶È²¼ÀÙGFS',7, getdate())
,(14,'ËáÀ±µ°¸ÉÄ¾¶úGFS',6, getdate())
,(14,'ÏãÀ±Ÿh³á', 8, getdate())
,(14,'Î÷À¼»¨',8, getdate())
,(14,'Ïã»¬Õôµ°', 6.5, getdate())
,(14,'¹¦·òËÍ´ó°×·¹',3, getdate())
,(14,'ÌïÔ°²Ê¶¹',4.5, getdate())
,(14,'²öÏãÕô½È',8, getdate())
,(15,'ËÄ¼¾Öí¹ÇÌÀ',14, getdate())
,(15,'ÖñË¿¼¦ÌÀ',15, getdate())
,(16,'ÇÑ×Ó·¹',15.5, getdate())
,(16,'ÇÑ×ÓÒûÁÏÌ×',20, getdate())
,(16,'ÇÑ×Ó·¹´óÂú×ãÒûÁÏÌ×', 44.5, getdate())
,(16,'ÇÑ×ÓÌÀÌ×²Í',22, getdate())
,(17,'ÏÊÀ±ÅÅ¹Ç·¹', 19.5, getdate())
,(17,'À±¹ÇÒûÁÏÌ×²Í', 29.5, getdate())
,(17,'ÏÊÀ±ÅÅ¹Ç²Ë', 16.5, getdate())
,(17,'À±¹Ç·¹³£¹æÒûÁÏÌ×+Õôµ°', 36, getdate())
,(17,'ÏÊÀ±ÅÅ¹ÇÌÀÌ×²Í', 31.5, getdate())
,(17,'À±¹ÇÌ×·¹', 21.5, getdate())
,(18,'Ëá²ËÂ±Èâ·¹', 18.5, getdate())
,(18,'Â±ÈâÌ×·¹', 20.5, getdate())
,(18,'Ëá²ËÂ±ÈâÂú×ã²Í', 34.5, getdate())
,(18,'Â±Èâ·¹³£¹æÒûÁÏÌ×+Õôµ°', 33, getdate())
,(18,'Ëá²ËÂ±ÈâÌÀÌ×²Í', 28.5, getdate())
,(19,'¼¦ÍÈÈâÌÀÌ×ÔùÕôµ°GFS',28.5, getdate())
,(19,'¼¦ÍÈÎ÷À¼»¨ÌÀÌ×²Í', 30.5, getdate())
,(19,'¶¬¹½¼¦ÍÈÈâ·¹', 17.5, getdate())
,(19,'¼¦ÍÈÌ×·¹', 19.5, getdate())
,(19,'¶¬£¨Ïã£©¹½¼¦ÍÈÈâÂú×ã²Í', 34.5, getdate())
,(19,'¼¦ÍÈÒûÁÏÌ×²Í', 26.5, getdate())
,(19,'¶¬¹½¼¦ÍÈÈâ²Ë', 14.5, getdate())
,(19,'¶¬¹½¼¦ÍÈÈâÌÀÌ×²Í',28.5, getdate())
,(19,'ËáÀ±µ°¸É¼¦ÍÈÈâÌÀÌ×²ÍGFS',44, getdate())
,(19,'¼¦ÍÈÈâÑ©ÀæÂ¶Ì×²ÍGFS', 44, getdate())
,(20,'ËáÀ±µ°¸ÉË«Æ´ÌÀÌ×²ÍGFS', 40.5, getdate())
,(20,'ÅÅ¹ÇÆ´¼¦ÍÈÈâ·¹', 20.5, getdate())
,(20,'Ë«Æ´ºÀ»ªµ¥ÈËÌÀÌ×',42.5, getdate())
,(20,'ÅÅ¹ÇË«Æ´ÒûÁÏÌ×', 30.5, getdate())
,(20,'ÅÅ¹ÇÆ´¼¦ÍÈÈâ²Ë', 17.5, getdate())
,(20,'ÅÅ¹ÇÆ´¼¦ÍÈÈâ·¹ÌÀÌ×²Í', 32.5, getdate())
,(21,'½´Ïã»¨ëîË«ÈËÌ×²ÍGFS',60, getdate())
,(21,'½´Ïã»¨ëîÅÅ¹ÇË«ÈË²ÍGFS', 70.5, getdate())
,(21,'Ë«ÈËÄÜÁ¿²Í¼ÓÏíÊÀ½ç±­Àñ°üGFS', 194, getdate())
,(21,'¾­µäÅÅ¹ÇË«ÈË²ÍGFS',72.5, getdate())
,(21,'Â±Èâ¶¬¹½¼¦Ë«ÈË²ÍGFS',66.5, getdate())
,(21,'Ë«ÈËÌ×²Í£¨·Ç»ªÄÏ£©', 60, getdate())
,(21,'À±¹Ç¼¦ÍÈË«ÈË³©ÒûÌ×²ÍGFS', 69.5, getdate())
,(22,'ÏÄÈÕ±ùË¬ÏÂÎç²èÌ×²ÍGFS',16, getdate())
,(22,'ÏÄÈÕ±ùË¬ÏÂÎç²èÌ×²ÍGFS',	29, getdate())
,(22,'Ş²ÈÊË¬²¼ÀÙGFS',16, getdate())
,(22,'Ş²ÈÊË¬µ°¸ÉÄ¾¶úGFS',16, getdate())
,(22,'Ş²ÈÊË¬Õôµ°GFS', 16, getdate())
,(22,'ÏÊÏãÃ×·ÛÖí¹ÇÌÀ',15, getdate())
,(22,'Õô½È+Ğ¡¶¹½¬', 10, getdate())
,(22,'À±³áÓãÍèÆ´ÅÌGFS', 35, getdate())
,(22,'Èâ½´Ã×·Û+¶¹½¬', 11, getdate())
,(22,'²öÏãÕô½È', 8, getdate())
,(22,'ÏÊÏãÈâ½´ÕôÃ×·Û+ÈÈ¶¹½¬£¨Ğ¡£©',10, getdate())
,(23,'ÏÊÏãÃ×·ÛÖí¹ÇÌÀ',15, getdate())
,(24,'ÍâÆÅ²Ë',4, getdate())
,(24,'İ®¿ª¶ş¶È²¼ÀÙGFS',7, getdate())
,(24,'ËáÀ±µ°¸ÉÄ¾¶úGFS',6, getdate())
,(24,'ÏãÀ±Ÿh³á',8, getdate())
,(24,'Ïã»¬Õôµ°',6.5, getdate())
,(25,'ÄûÃÊŞ²ÈÊË¬GFS', 14, getdate())
,(25,'Òø¶úÑ©ÀæÂ¶GFS', 14, getdate())
,(25,'ÈÈ¶¹½¬£¨Ğ¡£©', 6, getdate())
,(25,'ÈÈ¶¹½¬',6, getdate())
,(25,'°ÙÊÂ¿ÉÀÖ',8, getdate())
,(25,'½ğĞÓÃÛÌÒÖ­',10, getdate())
select mid,mmtid,mname,mprice,mdiscount,mdate,mstatus from Menu
go
--¹ºÎï³µ
create table ShoppingCart
(
  scid int primary key identity(1,1),--Ö÷¼ü ×Ô¶¯ ±àºÅ
  scvip int foreign key references Vip(vid) not null,--»áÔ±
  scmenu int foreign key references Menu(mid) not null,--²Ëµ¥
  scquantity int default(1)--ÊıÁ¿
)
go
--insert into ShoppingCart(scvip,scmenu,scquantity) values(	1,2,2)
select * from ShoppingCart

--¶©µ¥±í
create table Orders
(
  oNumber varchar(32) UNIQUE not null,--¶©µ¥ºÅ
  oorderDate datetime not null default(getdate()),--¶©¹ºÊ±¼ä
  --oadvanceOf datetime,--Ô¤´ïÊ±¼ä
  --oname nvarchar(16) not null,--ÅÉËÍÈË
  --ophone char(13) not null,--ÅÉËÍÈËµç»°
  omoney money not null,--×Ü½ğ¶î
  --odiscount smallmoney default(1), --ÕÛ¿Û ÓÃÓÚÈ«³¡ÕÛ¿Û
  --odiscountMoney smallmoney not null,--ÕÛ¿Û½ğ¶î
  oinvoice bit default(0),--·¢Æ±
  --oonetableware bit default(0),--Ò»´ÎĞÔ²Í¾ß
  ovid int foreign key references Vip(vid) not null,--»áÔ±
  osid int foreign key references Locations(lid) not null,--µØÖ·
  oremark nvarchar(225),--±¸×¢
  ostatus tinyint default(0) --×´Ì¬ 0´ı¸¶¿î | 1ÒÔ½áÕË | 2ÒÑÍê³É | 
)
go
select * from Orders
select oNumber,oorderDate,omoney,oinvoice,ovid,osid,oremark,ostatus from Orders
				where ovid=1 and ostatus=1 ORDER BY oorderDate DESC
--Ïû·Ñ±í
create table Consumption
(
  cid int primary key identity(1,1),--Ö÷¼ü ×Ô¶¯ ±àºÅ
  coNumber varchar(32) foreign key references Orders(oNumber) not null,--¶©µ¥ºÅ
  cmid int foreign key references Menu(mid) not null,--²Ëµ¥
  cquantity int default(1),--ÊıÁ¿
)
go
select * from Consumption

--ÆÀ¼Û±í
create table Evaluate
(
 eid int primary key identity(1,1),--Ö÷¼ü ×Ô¶¯ ±àºÅ
 eoNumber varchar(32) foreign key references Orders(oNumber) not null,--¶©µ¥ºÅ
 eminute float(1) check(eminute>=0 and eminute<=5) default(0),--ÆÀ¼Û¼°¸ñ 5-0·Ö
 eremark nvarchar(225)--±¸×¢
)
go

--´æ´¢¹ı³Ì 
go
create procedure selectMenuType
@mtid int
as
	select mtname from MenuType mt inner join Menu m on mt.mtid=m.mid where mtid=@mtid
go
exec selectMenuType 2
go
create procedure pro_select
@page int,@num int
as
declare @truePage int
set @truePage=(@page-1)*@num --¹«Ê½
	select top(@num) * from Menu m inner join MenuType mt on m.mmtid=mt.mtid where mid not in (select top(@truePage) mid from Menu)
go
exec pro_select 1,16
go
go

