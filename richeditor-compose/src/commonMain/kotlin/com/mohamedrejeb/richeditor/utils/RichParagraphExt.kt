package com.mohamedrejeb.richeditor.utils

import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.PlatformParagraphStyle
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.util.fastForEach
import com.mohamedrejeb.richeditor.paragraph.RichParagraph
import com.mohamedrejeb.richeditor.paragraph.type.ParagraphType

internal fun List<RichParagraph>.getCommonStyle(): ParagraphStyle? {
    if (this.isEmpty()) return null

    val firstParagraphStyle = this.firstOrNull()?.paragraphStyle ?: return null
    val otherStyleList = this.drop(1)

    var textAlign: TextAlign? = firstParagraphStyle.textAlign
    var textDirection: TextDirection? = firstParagraphStyle.textDirection
    var lineHeight: TextUnit = firstParagraphStyle.lineHeight
    var textIndent: TextIndent? = firstParagraphStyle.textIndent
    var platformStyle: PlatformParagraphStyle? = firstParagraphStyle.platformStyle
    var lineHeightStyle: LineHeightStyle? = firstParagraphStyle.lineHeightStyle
    var lineBreak: LineBreak? = firstParagraphStyle.lineBreak
    var hyphens: Hyphens? = firstParagraphStyle.hyphens

    otherStyleList.fastForEach {
        val otherParagraphStyle = it.paragraphStyle
        if (otherParagraphStyle.textAlign != textAlign) textAlign = null
        if (otherParagraphStyle.textDirection != textDirection) textDirection = null
        if (otherParagraphStyle.lineHeight != lineHeight) lineHeight = TextUnit.Unspecified
        if (otherParagraphStyle.textIndent != textIndent) textIndent = null
        if (otherParagraphStyle.platformStyle != platformStyle) platformStyle = null
        if (otherParagraphStyle.lineHeightStyle != lineHeightStyle) lineHeightStyle = null
        if (otherParagraphStyle.lineBreak != lineBreak) lineBreak = null
        if (otherParagraphStyle.hyphens != hyphens) hyphens = null
    }

    return ParagraphStyle(
        textAlign = textAlign ?: TextAlign.Unspecified,
        textDirection = textDirection ?: TextDirection.Unspecified,
        lineHeight = lineHeight,
        textIndent = textIndent,
        platformStyle = platformStyle,
        lineHeightStyle = lineHeightStyle,
        lineBreak = lineBreak ?: LineBreak.Unspecified,
        hyphens = hyphens ?: Hyphens.Unspecified,
    )
}

internal fun List<RichParagraph>.getCommonType(): ParagraphType? {
    var type: ParagraphType? = null

    for (paragraph in this) {
        if (type == null)
            type = paragraph.type
        else
            if (type != paragraph.type) return null
    }

    return type
}