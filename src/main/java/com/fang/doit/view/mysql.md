#### 1.数据库隔离级别有哪些，各自的含义是什么，MYSQL默认的隔离级别是是什么
> 1. 读未提交: 可读到别的事务未提交数据。
> 2. 读提交(RC): 读别的事务提交返回最新值无视图概念。不可重复读
> 3. 不可重复读: InnoDB默认前后两次读取一致。幻读
> 4. 串行化: 强制事务排序读加读锁写加写锁


#### 2.什么是幻读
指一个事务在前后两次查询同一个范围的时候，后一次查到了前一次查询没有看到的行
说明
> 1. 在RR级别下普通查询是快照读不会看到别的事务插入的数据的。因此幻读在当前读才会出现
> 2. 幻读专指新插入的行
当前读
select...lock in share mode ,(共享锁)
select...for update(排他锁)
update , delete , insert(排他锁)

#### 一致性视图MVCC
注意两个点：一个是由trx_id + roll_pointer(找到某一条记录)组成的版本链；一个是视图ReadView

#### 3.MYSQL有哪些存储引擎，各自优缺点
MyISAM 
> 1. 不支持事务；表级锁定；读写阻塞
> 2. 表级锁定，并发性能大大降低
> 3. 读写互相阻塞
> 4. 查询速度快。a.MyISAM存储的直接是文件的offset b.不用维护mvcc

InnoDB
> 1. 良好的事务支持：支持事务隔离的四个级别
> 2. 行级锁定：使用间隙锁避免幻读
> 3. 外键约束

Memory

https://zhuanlan.zhihu.com/p/102147497


#### 4.高并发下，如何做到安全的修改同一行数据

悲观锁：select...for update / lock in share mode
乐观锁：version


#### 5.乐观锁和悲观锁是什么，INNODB的标准行级锁有哪2种，解释其含义
> 1. 共享锁（S）：允许一个事务去读一行，阻止其他事务获得相同数据集的排他锁
> 2. 排他锁（X)：允许获得排他锁的事务更新数据，阻止其他事务取得相同数据集的共享读锁和排他写锁


#### 6.SQL优化的一般步骤是什么，怎么看执行计划，如何理解其中各个字段的含义



#### 7.数据库会死锁吗，举一个死锁的例子，mysql怎么解决死锁

1. 两个相同区间的间隙锁之间互相插入
2. 对同样的聚簇索引加锁，即使两个session锁的对象不同但是加锁范围


#### 8.Mysql的索引原理，索引的类型有哪些，如何创建合理的索引，索引如何优化
1. B+树
2. 聚簇索引 & 非聚簇索引
3. 最左前缀原则 & 覆盖索引 & 索引下推
4. 普通索引&唯一索引选择（考虑change buffer）


#### 9.聚集索引和非聚集索引的区别
 叶子节点前者是数据集 后者存储的是主键ID


#### 10.select for update 是什么含义，会锁表还是锁行或是其他
> 行锁 + 间隙锁 = next-key 


#### 11.为什么要用Btree实现，它是怎么分裂的，什么时候分裂，为什么是平衡的
如果页满了就会分裂，向上或向叶子


#### 12.数据库的ACID是什么。
原子性，事务性，隔离性，一致性


#### 13.某个表有近千万数据，CRUD比较慢，如何优化
分表分库


#### 14.Mysql怎么优化table scan的



#### 15.如何写sql能够有效的使用到复合索引



#### 16.mysql中 in 和exists 区别



#### 17.数据库自增主键可能的问题
> 分表的情况下出现ID重复的


#### 18.MVCC的含义，如何实现的
> undo log回滚日志记录每次视图数据


#### 19.你做过的项目里遇到分库分表了吗，怎么做的，有用到中间件么，比如sharding jdbc等,他们的原理知道么



#### 20.MYSQL的主从延迟怎么解决
主从时间计算：
    1.A完成事务写入binlog 记为T1； 
    2.B接受完binlog记为T2； 
    3.B执行事务结束为T3
   每个binlog都会有时间段记入主库的写入时间，备库可取出此字段计算备份时间（开始备库会计算与主库的时间差值并在计算时候消除）
主从延迟原因：
    1.备库的性能比主库差，并设置"双非1"模式
    2.备库压力大。一些后台查询可通过binlog输出到外部系统如Hadoop，外部系统提供统计类查询
    3.大事务。主库执行10分钟备库延迟10分钟
    4.并行复制能力。sql->data

#### 主备切换策略
1.可靠性优先
   A设置readonly=true --> seconds_behind_master=0 -->B设置readonly=false可读写 -->业务到B
   一段时间被不可用一般难接受
2.可用性优先
   先开始后两步但可能数据不一致，如连续插入4,5记录在A(4,4)之后备份到B，但此时切换到B之后插入成(4,5)。
   binlog_format = row 记录插入行的所有日志。在上面情况下此会异常插入失败
   binlog_format = mixed 记录变更数据
   

















