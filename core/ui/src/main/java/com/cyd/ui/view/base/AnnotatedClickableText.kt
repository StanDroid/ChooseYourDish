package com.cyd.ui.view.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun AnnotatedClickableText(
    modifier: Modifier = Modifier,
    str: String,
    link: String,
    style: TextStyle = TextStyle.Default,
) {
    if (str.isEmpty()) return
    val annotatedLinkString: AnnotatedString = buildAnnotatedString {
        append(str)
        addStyle(
            style = SpanStyle(
                color = Color(0xff64B5F6),
                fontSize = style.fontSize,
                textDecoration = TextDecoration.Underline
            ), start = 0, end = str.length
        )

        // attach a string annotation that stores a URL to the text "link"
        addStringAnnotation(
            tag = "URL",
            annotation = link,
            start = 0,
            end = str.length - 1
        )
    }
    val uriHandler = LocalUriHandler.current
    ClickableText(
        modifier = modifier.fillMaxWidth(),
        text = annotatedLinkString,
        onClick = {
            annotatedLinkString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { string ->
                    uriHandler.openUri(string.item)
                }
        },
        style = style
    )
}