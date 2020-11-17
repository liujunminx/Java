-- 积分result表中有A B C D四列，要求：
-- 1）当A列值大于等于B列时，选择A列否则选择B列
-- 2）当C列值大于等于D列时，选择C列否则选择D列
select (case when A >= B then A else B end) MAX_BC, (case when C >= D then C else D end ) MAX_CD from result;
-- 设置某个字段缺省值
alter table tb_name alter column_name set default xxx;
# 某打车公司将驾驶里程（drivedistanced）超过5000里的司机信息转移到一张称为seniordrivers 的表中,他们的详细情况被记录在表drivers 中，正确的sql为（）
select * into seniordrivers(drivedistanced) from drivers where drivedistanced > 5000
# 有订单表orders，包含字段用户信息userid，字段产品信息productid，以下语句能够返回至少被订购过两次的productid？（）
select productid from orders group by productid having count(productid) > 1;