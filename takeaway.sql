use master
go

if exists(select * from sys.databases where name='TakeawayDB')
  drop database TakeawayDB
go

create database TakeawayDB
go

use TakeawayDB
go

--��Ա���ͱ�
create table VipType
(
  vtid int primary key identity(1,1),--���� �Զ� ���
  vtname varchar(16) unique not null,--Ψһ �ǿ� ����
  vtdiscount smallmoney default(1) --�ۿ�
)
go
insert into VipType( vtname, vtdiscount) values
( 'v5', 1)
,( 'v2', 0.98)
,( 'v3', 0.95)
,( 'v4', 0.90)
select * from vipType
go

--��Ա��
create table Vip
(
  vid int primary key identity(1,1),--���� �Զ�	���
  vaccountNumber char(13) UNIQUE not null,--�˺� �̶� Ψһ
  vpassword varchar(16) not null,--��¼����
  vvtid int foreign key references vipType(vtid) not null,--��Ա����
  vname nvarchar(16),--����
  vsex nvarchar(1) check(vsex='��'or vsex='Ů') default('��'),--�Ա� 1�� | 0Ů
  vheadPortrait nvarchar(225),--ͷ��
  vbirthday date,--����
  vregion nvarchar(225), --����
  vemail varchar(100),--��������
  vflavor nvarchar(64),--��ζϲ��
  vpersonality nvarchar(64),--���Ա�ǩ
  vintegral int default(0),--����
  vbalance money default(0),--���
  vpaymentPassword char(6)--֧������ �̶�
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

--��Ա��ַ��
create table Locations
(
  lid  int primary key identity(1,1),--���� �Զ� ���
  lvid int foreign key references Vip(vid) not null,--��Ա
  lname nvarchar(16) not null,--��ϵ������
  lsex varchar(2) check(lsex='��'or lsex='Ů') default('��'),--�Ա� 1�� | 0Ů,--�Ա�
  lphone char(13) not null,--��ϵ�绰 �̶�
  laddress nvarchar(225),--��ϸ��ַ
  lstatus bit default(0) -- 1Ĭ��
)
go
insert into Locations(lvid,lname,lsex,lphone,laddress,lstatus) values(1,'Ůʿ','Ů','15070237084','�Ϸ�IT',0)
--delete Locations where lvid=4
select * from Locations 
select * from Locations where lvid=1 and lstatus=1
go




--�˵�����
create table MenuType
(
  mtid int primary key identity(1,1),--���� �Զ�	���
  mtname nvarchar(16) not null,--����
  mtParentClass int default(0),--����
)
go
insert into menuType(mtname,mtParentClass) values
('��ֵ',0)
,('Ӫ�����',0)
,('�ḻ��ʳ',0)
,('���������',0)
,('�ײ�Ӫ����',2)
,('���籭���',3)
,('�����ײ�', 3)
,('���㻨�', 3)
,('��֭�ŹǷ�', 3)
,('��������֭�ŹǷ�', 3)
,('��Ʒ-�ߵ�����������', 3)
,('���������ײ�', 3)
,('������ţ��', 3)
,('���/�׷�', 3)
,('����', 3)
,('�������ӷ�', 3)
,('�����ŹǷ�', 3)
,('���±�ⷹ', 3)
,('�����㣩�������ⷹ', 3)
,('�Ź�ƴ�����ⷹ', 3)
,('��ֵ˫�˲�', 3)
,('�ײ�', 4)
,('�׷ۡ�����', 4)
,('С��', 4)
,('��Ʒ', 4)
select * from menuType where mtParentClass = 0
go


--�˵�
create table Menu
(
  mid  int primary key identity(1,1),--���� �Զ� ���
  mmtid int foreign key references MenuType(mtid) not null,--�˵�����
  mname nvarchar(16) not null,--����
  mprice smallmoney not null,--ԭ��
  mdiscount smallmoney default(1), --�ۿ�
  mdate datetime default(getdate()),--���ʱ��
  mstatus bit default(0)--״̬ 0�ϼ� | 1�¼�
)
go
insert into Menu(mmtid, mname,mprice,mdate) values
(1,'���������ͼ������籭���GFS', 161.6,getdate())
,(1,'˫�������ͼ������籭���GFS', 194.4,getdate())
,(1,'�����Ź�˫�˲�GFS', 72.5,getdate())
,(1,'±�ⶬ����˫�˲�GFS', 66.5, getdate())
,(1,'���㻨���ڼ����ײ�GFS', 26.4, getdate())
,(1,'���㻨�������GFS', 40,getdate())
,(1,'���㻨�������GFS', 36,getdate())
,(5,'�����������GFS', 16, getdate())
,(6,'���������ͼ������籭���GFS',161.6, getdate())
,(6,'˫�������ͼ������籭���GFS', 194.4, getdate())
,(7,'˫ƴ±���������Ӳ�GFS', 84, getdate())
,(8,'���㻨��ѩ��¶�ײ�GFS',28, getdate())
,(8,'���㻨��޲��ˬ�ײ�GFS',28, getdate())
,(8,'���㻨��˫���ײ�GFS',60, getdate())
,(8,'���㻨���Ź�˫�˲�GFS', 70.55, getdate())
,(8,'���㻨���ڼ����ײ�GFS', 26.4, getdate())
,(8,'���㻨�GFS',18, getdate())
,(8,'���㻨�������GFS', 40, getdate())
,(8,'���㻨�������GFS',36, getdate())
,(8,'���㻨�����ײ�GFS', 28, getdate())
,(9,'�Ź����ײ�',31.5 , getdate())
,(9,'�Ź��׷�', 21.5, getdate())
,(9,'��֭�ŹǷ�',19.5, getdate())
,(9,'�Ź������ײ�',29.5, getdate())
,(9,'��֭�Źǲ�',15.5, getdate())
,(10,'���������Ź����ײ�GFS',39.5, getdate())
,(11,'�������ף������h�ᣩ',33, getdate())
,(11,'�������ף�����������',31, getdate())
,(11,'�ߵ����������������ײ�',30, getdate())
,(11,'�ߵ����������躬���ײ�',30, getdate())
,(11,'�ߵ����������跹', 20, getdate())
,(11,'�ߵ������������', 17, getdate())
,(12,'���Ǻ�����������', 40.5, getdate())
,(12,'���±�������������',38.5, getdate())
,(12,'��֭�Ź������',36.5, getdate())
,(12,'�����Ź������',36.5, getdate())
,(12,'���������������������', 38.5, getdate())
,(12,'��֭�ŹǺ�����������',40.5, getdate())
,(13,'������ţ��', 17.5, getdate())
,(13,'������ţ��', 20.5, getdate())
,(13,'��ţ�׷�',23, getdate())
,(13,'��ţ������',30, getdate())
,(13,'������ţ�����ײ�',32, getdate())
,(14,'���Ų�',4, getdate())
,(14,'ݮ�����Ȳ���GFS',7, getdate())
,(14,'��������ľ��GFS',6, getdate())
,(14,'�����h��', 8, getdate())
,(14,'������',8, getdate())
,(14,'�㻬����', 6.5, getdate())
,(14,'�����ʹ�׷�',3, getdate())
,(14,'��԰�ʶ�',4.5, getdate())
,(14,'��������',8, getdate())
,(15,'�ļ������',14, getdate())
,(15,'��˿����',15, getdate())
,(16,'���ӷ�',15.5, getdate())
,(16,'����������',20, getdate())
,(16,'���ӷ�������������', 44.5, getdate())
,(16,'�������ײ�',22, getdate())
,(17,'�����ŹǷ�', 19.5, getdate())
,(17,'���������ײ�', 29.5, getdate())
,(17,'�����Źǲ�', 16.5, getdate())
,(17,'���Ƿ�����������+����', 36, getdate())
,(17,'�����Ź����ײ�', 31.5, getdate())
,(17,'�����׷�', 21.5, getdate())
,(18,'���±�ⷹ', 18.5, getdate())
,(18,'±���׷�', 20.5, getdate())
,(18,'���±�������', 34.5, getdate())
,(18,'±�ⷹ����������+����', 33, getdate())
,(18,'���±�����ײ�', 28.5, getdate())
,(19,'����������������GFS',28.5, getdate())
,(19,'�������������ײ�', 30.5, getdate())
,(19,'���������ⷹ', 17.5, getdate())
,(19,'�����׷�', 19.5, getdate())
,(19,'�����㣩�������������', 34.5, getdate())
,(19,'���������ײ�', 26.5, getdate())
,(19,'�����������', 14.5, getdate())
,(19,'�������������ײ�',28.5, getdate())
,(19,'�������ɼ��������ײ�GFS',44, getdate())
,(19,'������ѩ��¶�ײ�GFS', 44, getdate())
,(20,'��������˫ƴ���ײ�GFS', 40.5, getdate())
,(20,'�Ź�ƴ�����ⷹ', 20.5, getdate())
,(20,'˫ƴ������������',42.5, getdate())
,(20,'�Ź�˫ƴ������', 30.5, getdate())
,(20,'�Ź�ƴ�������', 17.5, getdate())
,(20,'�Ź�ƴ�����ⷹ���ײ�', 32.5, getdate())
,(21,'���㻨��˫���ײ�GFS',60, getdate())
,(21,'���㻨���Ź�˫�˲�GFS', 70.5, getdate())
,(21,'˫�������ͼ������籭���GFS', 194, getdate())
,(21,'�����Ź�˫�˲�GFS',72.5, getdate())
,(21,'±�ⶬ����˫�˲�GFS',66.5, getdate())
,(21,'˫���ײͣ��ǻ��ϣ�', 60, getdate())
,(21,'���Ǽ���˫�˳����ײ�GFS', 69.5, getdate())
,(22,'���ձ�ˬ������ײ�GFS',16, getdate())
,(22,'���ձ�ˬ������ײ�GFS',	29, getdate())
,(22,'޲��ˬ����GFS',16, getdate())
,(22,'޲��ˬ����ľ��GFS',16, getdate())
,(22,'޲��ˬ����GFS', 16, getdate())
,(22,'�����׷������',15, getdate())
,(22,'����+С����', 10, getdate())
,(22,'��������ƴ��GFS', 35, getdate())
,(22,'�⽴�׷�+����', 11, getdate())
,(22,'��������', 8, getdate())
,(22,'�����⽴���׷�+�ȶ�����С��',10, getdate())
,(23,'�����׷������',15, getdate())
,(24,'���Ų�',4, getdate())
,(24,'ݮ�����Ȳ���GFS',7, getdate())
,(24,'��������ľ��GFS',6, getdate())
,(24,'�����h��',8, getdate())
,(24,'�㻬����',6.5, getdate())
,(25,'����޲��ˬGFS', 14, getdate())
,(25,'����ѩ��¶GFS', 14, getdate())
,(25,'�ȶ�����С��', 6, getdate())
,(25,'�ȶ���',6, getdate())
,(25,'���¿���',8, getdate())
,(25,'��������֭',10, getdate())
select mid,mmtid,mname,mprice,mdiscount,mdate,mstatus from Menu
go
--���ﳵ
create table ShoppingCart
(
  scid int primary key identity(1,1),--���� �Զ� ���
  scvip int foreign key references Vip(vid) not null,--��Ա
  scmenu int foreign key references Menu(mid) not null,--�˵�
  scquantity int default(1)--����
)
go
--insert into ShoppingCart(scvip,scmenu,scquantity) values(	1,2,2)
select * from ShoppingCart

--������
create table Orders
(
  oNumber varchar(32) UNIQUE not null,--������
  oorderDate datetime not null default(getdate()),--����ʱ��
  --oadvanceOf datetime,--Ԥ��ʱ��
  --oname nvarchar(16) not null,--������
  --ophone char(13) not null,--�����˵绰
  omoney money not null,--�ܽ��
  --odiscount smallmoney default(1), --�ۿ� ����ȫ���ۿ�
  --odiscountMoney smallmoney not null,--�ۿ۽��
  oinvoice bit default(0),--��Ʊ
  --oonetableware bit default(0),--һ���Բ;�
  ovid int foreign key references Vip(vid) not null,--��Ա
  osid int foreign key references Locations(lid) not null,--��ַ
  oremark nvarchar(225),--��ע
  ostatus tinyint default(0) --״̬ 0������ | 1�Խ��� | 2����� | 
)
go
select * from Orders
select oNumber,oorderDate,omoney,oinvoice,ovid,osid,oremark,ostatus from Orders
				where ovid=1 and ostatus=1 ORDER BY oorderDate DESC
--���ѱ�
create table Consumption
(
  cid int primary key identity(1,1),--���� �Զ� ���
  coNumber varchar(32) foreign key references Orders(oNumber) not null,--������
  cmid int foreign key references Menu(mid) not null,--�˵�
  cquantity int default(1),--����
)
go
select * from Consumption

--���۱�
create table Evaluate
(
 eid int primary key identity(1,1),--���� �Զ� ���
 eoNumber varchar(32) foreign key references Orders(oNumber) not null,--������
 eminute float(1) check(eminute>=0 and eminute<=5) default(0),--���ۼ��� 5-0��
 eremark nvarchar(225)--��ע
)
go

--�洢���� 
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
set @truePage=(@page-1)*@num --��ʽ
	select top(@num) * from Menu m inner join MenuType mt on m.mmtid=mt.mtid where mid not in (select top(@truePage) mid from Menu)
go
exec pro_select 1,16
go
go

