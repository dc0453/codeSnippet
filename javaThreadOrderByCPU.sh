#!/bin/sh
# use it in linux
ts=$(date +"%s")
jvmPid=$1
defaultLines=100
defaultTop=20

threadStackLines=${2:-$defaultLines}
topThreads=${3:-$defaultTop}

jvmCapture=$(top -b -n1 | grep java )
threadsTopCapture=$(top -b -n1 -H | grep java )
jstackOutput=$(echo "$(jstack $jvmPid )" )
topOutput=$(echo "$(echo "$threadsTopCapture" | head -n $topThreads | perl -pe 's/\e\[?.*?[\@-~] ?//g' | awk '{gsub(/^ +/,"");print}' | awk '{gsub(/ +|[+-]/," ");print}' | cut -d " " -f 1,9 )\n ")

echo "*************************************************************************************************************"

uptime

echo "Analyzing top $topThreads threads"

echo "*************************************************************************************************************"

printf %s "$topOutput" | while IFS= read  line

do
   pid=$(echo $line | cut -d " " -f 1)
   hexapid=$(printf "%x" $pid)
   cpu=$(echo $line | cut -d " " -f 2)
   echo -n $cpu"% [$pid] " 
   echo "$jstackOutput" | grep "tid.*0x$hexapid " -A $threadStackLines | sed -n -e '/0x'$hexapid'/,/tid/ p' | head -n -1
   echo "\n"
done

echo "\n" 
