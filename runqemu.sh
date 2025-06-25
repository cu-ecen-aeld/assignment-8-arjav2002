#!/bin/bash
# Script to start qemu

source poky/oe-init-build-env
export LC_ALL="en_US.UTF-8"
export LC_CTYPE="en_US.UTF-8"
export QB_SLIRP_OPT="-netdev user,id=net0,hostfwd=tcp::10022-:22,hostfwd=tcp::9000-:9000"
runqemu slirp nographic
