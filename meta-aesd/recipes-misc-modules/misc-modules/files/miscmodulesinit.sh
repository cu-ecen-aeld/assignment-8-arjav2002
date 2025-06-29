#!/bin/sh

case $1 in
	start)
		modprobe hello
		cp /lib/modules/`uname -r`/extra/faulty.ko /
		start-stop-daemon -S -n faultyinit -a /bin/module_load -- faulty
		;;
	stop)
		rmmod hello
		start-stop-daemon -K -n faultyinit
		start-stop-daemon -S -n faultyteardown -a /bin/module_unload
		;;
	*)
		echo "Usage is {start|stop}"
	exit 1
esac
exit 0

