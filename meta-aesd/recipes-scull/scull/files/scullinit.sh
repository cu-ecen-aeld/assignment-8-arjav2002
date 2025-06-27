#!/bin/sh

case $1
	start)
		start-stop-daemon -S -n scullinit -a /bin/scull_load
		;;
	stop)
		start-stop-daemon -K -n scullinit
		start-stop-daemon -S -n scullteardown -a /bin/scull_unload
		;;
	*)
		echo "Usage is {start|stop}"
	exit 1
esac
exit 0

