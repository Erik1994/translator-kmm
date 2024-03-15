package com.example.kmptranslator.android.core.presentation.permission

import android.Manifest

enum class Permissions(val permission: String) {
    RECORD_AUDIO(Manifest.permission.RECORD_AUDIO);

    companion object {
        fun getPermissionArray(): Array<String> {
            val permissionArray = entries.toTypedArray()
            return buildList {
                for (i in permissionArray.indices) {
                    add(permissionArray[i].permission)
                }
            }.toTypedArray()
        }

        fun getPermissionByValue(value: String) = entries.find { it.permission == value }
    }
}