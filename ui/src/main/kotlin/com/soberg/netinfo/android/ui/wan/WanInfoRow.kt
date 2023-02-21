@file:JvmName("WanInfoRow")

package com.soberg.netinfo.android.ui.wan

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.soberg.netinfo.android.ui.R
import com.soberg.netinfo.android.ui.preview.ThemedPreview
import com.soberg.netinfo.android.ui.theme.Dimens

@Composable
fun WanInfoRow(
    ipAddressString: String,
) {
    Row(
        modifier = Modifier.wrapContentSize()
            .padding(Dimens.Padding.Base25),
        horizontalArrangement = Arrangement.spacedBy(Dimens.Padding.Base25),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Icon(
            painter = painterResource(R.drawable.ic_wifi),
            contentDescription = null,
        )

        Text(
            text = ipAddressString,
        )
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Large Font", fontScale = 2f)
@Composable
private fun WanInfoRowPreview() = ThemedPreview {
    WanInfoRow(
        ipAddressString = "192.168.0.101"
    )
}