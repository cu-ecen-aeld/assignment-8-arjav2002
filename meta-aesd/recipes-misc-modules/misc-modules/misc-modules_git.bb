# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE

inherit module
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-arjav2002.git;protocol=ssh;branch=main \
           file://0001-change-makefile.patch \
           file://miscmodulesinit.sh"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "8ced959ea5666b1b9622611fd50cb9adf3f9691e"

S = "${WORKDIR}/git"

FILES:${PN} += "${sysconfdir}/init.d/miscmodulesinit.sh"
FILES:${PN} += "${base_bindir}/module_load"
FILES:${PN} += "${base_bindir}/module_unload"

EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/misc-modules"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "miscmodulesinit.sh"

do_install() {
    module_do_install
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/miscmodulesinit.sh ${D}${sysconfdir}/init.d
    install -d ${D}${base_bindir}
    install -m 0755 ${S}/misc-modules/module_load ${D}${base_bindir}/
    install -m 0755 ${S}/misc-modules/module_unload ${D}${base_bindir}/
#    install -m 0755 ${S}/scull/scull.ko ${D}${sysconfdir}/init.d
#    install -m 0755 ${D}/lib/module/${KERNEL_VERSION}/extra/scull.ko ${D}
}
