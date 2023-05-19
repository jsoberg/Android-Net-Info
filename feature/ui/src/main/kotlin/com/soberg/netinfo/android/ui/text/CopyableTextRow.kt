package com.soberg.netinfo.android.ui.text

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soberg.netinfo.android.ui.R
import com.soberg.netinfo.android.ui.preview.ThemedPreview
import com.soberg.netinfo.android.ui.theme.Dimens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CopyableTextRow(
    modifier: Modifier = Modifier,
    text: String,
    onCopyTextClicked: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .clickable {
                onCopyTextClicked(text)
            }
            .padding(Dimens.Padding.Base25),
        horizontalArrangement = Arrangement.spacedBy(Dimens.Padding.Base25),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Icon(
            modifier = Modifier
                .size(24.dp),
            painter = painterResource(R.drawable.ic_content_copy),
            contentDescription = null,
        )

        Text(
            modifier = Modifier
                .basicMarquee(),
            text = text,
            fontSize = Dimens.FontSize.Large,
        )
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Small Font", fontScale = .5f)
@Preview(name = "Large Font", fontScale = 2f)
@Composable
private fun CopyableTextRowPreview() = ThemedPreview {
    CopyableTextRow(
        text = "192.168.0.101",
        onCopyTextClicked = { }
    )
}