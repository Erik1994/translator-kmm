package com.example.kmptranslator.android.core.presentation.permission

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun BuildSinglePermissionActivityContract(
    onPermissionLauncher: @Composable (ManagedActivityResultLauncher<String, Boolean>) -> Unit,
    onResult: (Boolean) -> Unit
) {
    val singlePermissionContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            onResult(isGranted)
        }
    )

    onPermissionLauncher(singlePermissionContract)
}

@Composable
fun BuildMultiplePermissionsActivityContract(
    onPermissionLauncher: @Composable (ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>) -> Unit,
    onResult: (Map<String, Boolean>) -> Unit
) {
    val multiplePermissionsContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { map ->
            onResult(map)
        }
    )

    onPermissionLauncher(multiplePermissionsContract)
}