#!/bin/sh -

source ~/.bash_profile

sql="select id,consumer from enterprise_order where order_time<'2017-01-01' limit 10"
echo $sql

#mysql_order_slave 是命令的别名，需要在bash_profile中创建数据库的连接
results=$(mysql_order_slave -e "$sql")
#echo $results

oIFS=$IFS
IFS=\r
rowNumber=0
for result in $results; do
  ((rowNumber++))
  if [[ $rowNumber -eq 1 ]]; then
    continue
  fi
  echo $rowNumber:$result
done
IFS=$oIFS
