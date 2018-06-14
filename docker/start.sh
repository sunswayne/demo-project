#!/bin/bash

function start(){
	cd ${APP_HOME}
	newFileName=`ls -rtl *.jar | grep ^[^d] | tail -n 1 | awk '{print $9}'`
	countFileName=`ls -l| grep ^[^d] |grep "^-"|wc -l`
	if [ $countFileName -gt 1 ]; then
		`ls *.jar | grep -v $newFileName | xargs rm`
	fi
	begin_length=`expr index "$newFileName" .`
	if [ $begin_length -gt 0 ]; then
		ext_name=${newFileName:$begin_length:${#newFileName}}
		if [ "$ext_name" = 'jar' ]; then
			echo "Run Program $newFileName java_opts = $1"
			java $1 -Duser.timezone=UTC+8 -Dfile.encoding=UTF-8 -Duser.language=en -Duser.country=US -jar $APP_HOME/$newFileName
		fi
	fi
}

java_opts="-Xms128m -Xmx256m"
if [ "$POPIN_JAVA_OPTS" ]; then
  java_opts=$POPIN_JAVA_OPTS
fi

start "$java_opts"

exit 0