package com.soberg.netinfo.android.ui.core.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview


@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Small Font", fontScale = .5f)
@Preview(name = "Large Font", fontScale = 2f)
@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION,
)
annotation class A11yPreview