#!/usr/bin/env bash

#注释包含以下几项
#脚本的参数
#脚本的用途
#脚本的注意事项
#脚本的写作时间，作者，版权等
#各个函数前的说明注释
#一些较复杂的单行命令注释


#判断参数个数
if [[ $# != 1 ]];then
    echo "Parameter incorrect."
    exit 1
fi


#source /etc/profile
#export PATH=”/usr/local/bin:/usr/bin:/bin:/usr/local/sbin:/usr/sbin:/sbin:/apps/bin/”

#另起一行使用\
#./configure \
#–prefix=/usr \
#–sbin-path=/usr/sbin/nginx \
#–conf-path=/etc/nginx/nginx.conf \

#在使用”$”来获取变量的时候最好加上双引号

#shell的结构
func1() {
	#do sth
}
func2() {
	#do sth
}
main() {
	func1
	func2
}
main "$@"

#变量作用域,默认都是全局的
var=1
func() {
	var=2
}
func
echo "$var"#输出是2，不是1
#使用local readonly这类的命令，或者使用declare来声明变量

#heredocs的使用,在”<<”后定一个标识符，接着我们可以输入多行内容，直到再次遇到标识符为止
#cat >> /etc/rsyncd.conf << EOF
#log file = /usr/local/logs/rsyncd.log
#transfer logging = yes
#log format = %t %a %m %f %b
#syslog facility = local3
#EOF

#获取当前脚本的路径
script_dir=$(cd $(dirname $0) && pwd)
script_dir=$(dirname $(readlink -f $0 ))


#尽量使用func(){}来定义函数，而不是func{}
#尽量使用[[]]来代替[]
#尽量使用$()将命令的结果赋给变量，而不是反引号
#在复杂的场景下尽量使用printf代替echo进行回显

#路径尽量保持绝对路径，绝多路径不容易出错，如果非要用相对路径，最好用./修饰
#优先使用bash的变量替换代替awk sed，这样更加简短
#简单的if尽量使用&& ||，写成单行。比如[[ x > 2]] && echo x
#当export变量时，尽量加上子脚本的namespace，保证变量不冲突
#会使用trap捕获信号，并在接受到终止信号时执行一些收尾工作
#使用mktemp生成临时文件或文件夹
#利用/dev/null过滤不友好的输出信息
#会利用命令的返回值判断命令的执行情况
#使用文件前要判断文件是否存在，否则做好异常处理
#不要处理ls后的数据(比如ls -l | awk '{ print $8 }')，ls的结果非常不确定，并且平台有关
#读取文件时不要使用for loop而要使用while read
