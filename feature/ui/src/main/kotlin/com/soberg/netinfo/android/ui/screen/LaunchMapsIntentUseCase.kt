package com.soberg.netinfo.android.ui.screen

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import com.soberg.netinfo.base.type.geodetic.Location
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class LaunchMapsIntentUseCase @Inject constructor(
    @ApplicationContext val context: Context,
) {
    operator fun invoke(location: Location) {
        val openMapUri = Uri.parse("geo:${location.latitude},${location.longitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, openMapUri).apply {
            flags = FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(mapIntent)
    }
}