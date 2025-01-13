plugins {
    id("dev.kikugie.stonecutter")
    id("fabric-loom") version "1.9-SNAPSHOT" apply false
    //id("dev.kikugie.j52j") version "1.0.2" apply false // Enables asset processing by writing json5 files
    //id("me.modmuss50.mod-publish-plugin") version "0.7.+" apply false // Publishes builds to hosting websites
}
stonecutter active "1.20.4"

stonecutter registerChiseled tasks.register("build-all", stonecutter.chiseled) {
    group = "project"
    ofTask("build")
}

stonecutter parameters {
    swap("mod_version", "\"${property("mod.version")}\";")

    const("release", property("mod.id") != "scratchmod")

    dependency("fapi", node!!.property("deps.fabric_api").toString())
}
