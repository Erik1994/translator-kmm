package com.example.kmptranslator.android.core.presentation.permission

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import com.example.kmptranslator.android.R

sealed class PermissionTextProvider(@StringRes val title: Int = R.string.permission_required) {

    abstract fun getDescription(isPermanentlyDeclined: Boolean): Int

    data object RecordingPermissionTextProvider : PermissionTextProvider() {
        override fun getDescription(isPermanentlyDeclined: Boolean): Int {
            return if (isPermanentlyDeclined) {
                R.string.permanently_declined_recording_permission_message
            } else {
                R.string.permanently_not_declined_recording_permission_message
            }
        }
    }

    private interface PermissionTextProviderI {
        fun getPermissionTextProvider(permission: Permissions): PermissionTextProvider
    }

    companion object : PermissionTextProviderI {
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        override fun getPermissionTextProvider(permission: Permissions): PermissionTextProvider {
            return when (permission) {
                Permissions.RECORD_AUDIO -> RecordingPermissionTextProvider
            }
        }
    }
}