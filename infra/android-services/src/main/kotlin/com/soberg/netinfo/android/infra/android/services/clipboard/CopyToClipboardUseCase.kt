package com.soberg.netinfo.android.infra.android.services.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CopyToClipboardUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val clipboardManager: ClipboardManager,
) {
    operator fun invoke(
        @StringRes
        labelResId: Int,
        valueToCopy: String,
    ) {
        clipboardManager.setPrimaryClip(
            ClipData.newPlainText(context.getString(labelResId), valueToCopy)
        )
    }
}